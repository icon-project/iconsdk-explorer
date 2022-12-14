import React, { Component } from 'react';
import {
    startsWith,
    findTabIndex,
    noSpaceLowerCase,
    isHxAddress,
    getChainInfo,
    getTabHash
} from 'utils/utils'
import {
    NotFoundPage,
    PendingPage
} from 'components';

class DetailPage extends Component {

    constructor(props) {
        super(props)
        this.state = {
            on: 0
        }
    }

    componentWillMount() {
        this.setInitialData(this.props.url)
    }

    componentWillReceiveProps(nextProps) {
        const { pathname: currentPath } = this.props.url
        const { pathname: nextPath } = nextProps.url
        const { ROUTE } = this.props
        if (currentPath !== nextPath && startsWith(nextPath, ROUTE)) {
            this.setInitialData(nextProps.url)
            return
        }
        else {
            const { hash: cHash} = this.props.url
            const currentHash = getTabHash(cHash)
            const { hash: nHash } = nextProps.url
            const nextHash = getTabHash(nHash)
            const { TABS: currentTabs } = this.props
            const { TABS: nextTabs } = nextProps
            if (currentHash !== nextHash || currentTabs.length !== nextTabs.length) {
                this.setTab(findTabIndex(nextTabs, nextHash))
            }    
        }
    }

    setInitialData = (url) => {
        const urlPath = url.pathname.split("/");
        const query = urlPath[2]
        const param = urlPath[3]
        if (query) {
            const { TABS } = this.props
            if(param) {
                this.props.getInfo(query, param)
            } else {
                this.props.getInfo(query)
            }
            const hash = getTabHash(url.hash)
            this.setTab(findTabIndex(TABS, hash), query)
        }
    }

    setTab = (index, query) => {
        const _index = index !== -1 ? index : 0
        this.setState({ on: _index },
            () => {
                this.setList(this.props.getList[_index], query)
            }
        )
    }

    setList = (getListFunc, query) => {
        const _query = query ? query : this.props.url.pathname.split("/")[2]
        const param = this.props.url.pathname.split("/")[3]
        if (typeof getListFunc === 'function') {
            if(param) {
                getListFunc(_query, param)
            } else {
                getListFunc(_query)
            }
        }
    }

    changeTab = (index) => {
        const { TABS, url } = this.props
        const { pathname } = url
        this.props.history.push(`${pathname}#${noSpaceLowerCase(TABS[index])}#${getChainInfo()}`);
    }

    render() {
        const { loading, error, pending } = this.props;
        const isNotFoundPage = !loading && error !== "" && !isHxAddress(error) && !pending

        const Content = () => {
            if(pending){
                return <PendingPage error={error}/>
            } 
            else if (isNotFoundPage) {
                return <NotFoundPage error={error}/>
            }
            else {
                const { InfoComponent, TabsComponent } = this.props
                return (
                    <div className="content-wrap">
                        <InfoComponent {...this.props}/>
                        <TabsComponent {...this.props} {...this.state} changeTab={this.changeTab}/>
                    </div>
                )
            }
        }
        return Content();
    }
}

export default DetailPage;
