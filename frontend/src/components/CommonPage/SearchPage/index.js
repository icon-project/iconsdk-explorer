import React, { Component } from 'react'
import queryString from 'query-string'
import SearchTableHead from './SearchTableHead'
import SearchTableBody from './SearchTableBody'
import SearchTableDesc from './SearchTableDesc'
import {
    LoadingComponent,
    Pagination,
    SortHolder,
    NoBox,
    SearchInput,
} from 'components'
import {calcMaxPageNum, getChainInfo, isNumeric} from 'utils/utils'
import { SEARCH_TYPE_DATA, CONTRACT_STATUS_NUM } from 'utils/const'

class SearchPage extends Component {
    constructor(props) {
        super(props)
        this.searchType = ''
        this.pageId = 1
        this._getList = () => {}
        this._getListSearch = () => {}
        this.state = {
            keyword: '',
            status: '',
            count: 25,
        }
    }

    componentWillMount() {
        this.initPage(this.props.url)
    }

    componentDidMount() {
        this.setInitialData(this.props.url)
    }

    componentWillReceiveProps(nextProps) {
        const { pathname: currentPath } = this.props.url
        const { pathname: nextPath } = nextProps.url
        const { search: currentSearch } = this.props.url
        const { search: nextSearch } = nextProps.url
        if (currentPath !== nextPath || currentSearch !== nextSearch) {
            this.setInitialData(nextProps.url)
        }
    }

    initPage = url => {
        this.getParams(url)
        this.getList(1, 0)
    }

    setInitialData = url => {
        this.getParams(url)
        this.setQueryToList(url.search)
    }

    setQueryToList = search => {
        const parsed = queryString.parse(search)
        const { pageId } = this
        const { keyword, count, status } = parsed
        this.getList(pageId, count, status, keyword)

        if (status) {
            this.setState({ status })
        }

        if (keyword) {
            this.setState({ keyword })
        }
    }

    getList = (page, count, status, keyword) => {
        const query = {
            page: isNumeric(page) ? page : 1,
            count: isNumeric(count) ? count : 25,
        }

        if (!!status && !!CONTRACT_STATUS_NUM[status]) {
            query.status = CONTRACT_STATUS_NUM[status]
        }

        if (!!keyword) {
            query.keyword = keyword
        }

        this._getList(query)
    }

    getSearchTypeData = () => {
        return SEARCH_TYPE_DATA[this.searchType] || {}
    }

    getCount = () => {
        const list = this.props[this.getSearchTypeData()['list']] || {}
        const { count } = list
        return count
    }

    getParams = url => {
        const { pathname } = url
        this.searchType = pathname.split('/')[1] || ''
        this._getList =
            this.props[this.getSearchTypeData()['getList']] || (() => {})
        this._getListSearch =
            this.props[`${this.getSearchTypeData()['getList']}Search`] ||
            (() => {})
        this.pageId = pathname.split('/')[2] || 1
    }

    getListByPage = page => {
        const count = this.getCount()
        const { status, keyword } = this.state
        const url = this.makeUrl(page, { count, status, keyword })
        this.props.history.push(url + `#${getChainInfo()}`)
    }

    getListByCount = count => {
        const { status, keyword } = this.state
        const url = this.makeUrl(1, { count, status, keyword })
        this.props.history.push(url + `#${getChainInfo()}`)
    }

    getListByStatus = status => {
        this.setState({ status }, () => {
            const { keyword } = this.state
            const count = this.getCount()
            const url = this.makeUrl(1, { count, status, keyword })
            this.props.history.push(url + `#${getChainInfo()}`)
        })
    }

    getListBySearch = nextSearch => {
        const { keyword } = this.state
        if (keyword === '' && nextSearch === '') {
            return
        }
        this.setState({ keyword: nextSearch }, () => {
            const { status } = this.state
            const count = this.getCount()
            const url = this.makeUrl(1, { count, status, keyword: nextSearch })
            this.props.history.push(url + `#${getChainInfo()}`)
        })
    }

    makeUrl = (page, query) => {
        let url = `/${this.searchType}`

        if (page) {
            url += `/${page}`
        }

        const isQuery = Object.keys(query).some(key => query[key])
        if (isQuery) {
            let firstQuery = true
            Object.keys(query).forEach(key => {
                if (query[key]) {
                    url += `${firstQuery ? '?' : '&'}${key}=${query[key]}`
                    firstQuery = false
                }
            })
        }

        return url
    }

    getNoData = (data, status) => {
        if (data && data.length !== 0) {
            return false
        } else if (status) {
            return false
        } else {
            return true
        }
    }

    render() {
        const list = this.props[this.getSearchTypeData()['list']] || {}
        const tableClassName = this.getSearchTypeData()['tableClassName'] || ''
        const contentsClassName =
            this.getSearchTypeData()['contentsClassName'] || ''
        const noBoxText = this.getSearchTypeData()['noBoxText'] || ''
        const placeholder = this.getSearchTypeData()['placeholder'] || ''
        const title = this.getSearchTypeData()['title'] || ''

        const { keyword, status } = this.state
        const { loading, data, page, listSize, count } = list
        const noData = this.getNoData(data, status)

        const TableContent = () => {
            if (noData) {
                return <NoBox text={keyword ? 'No Data' : noBoxText} />
            } else {
                return [
                    <div key="table-box" className="table-box">
                        <table className={tableClassName}>
                            <thead>
                                <SearchTableHead
                                    searchType={this.searchType}
                                    getListByStatus={this.getListByStatus}
                                />
                            </thead>
                            <tbody>
                                {data.map((item, index) => (
                                    <SearchTableBody
                                        key={index}
                                        data={item}
                                        searchType={this.searchType}
                                        index={index}
                                        count={count}
                                        page={page}
                                    />
                                ))}
                            </tbody>
                        </table>
                    </div>,
                    <SortHolder
                        key="SortHolder"
                        count={count >= 100 ? 100 : count}
                        getData={this.getListByCount}
                    />,
                    loading && (
                        <LoadingComponent
                            key="LoadingComponent"
                            style={{
                                position: 'absolute',
                                width: '0',
                                left: '185px',
                                bottom: '10px',
                            }}
                        />
                    ),
                    <Pagination
                        key="Pagination"
                        pageNum={page}
                        maxPageNum={calcMaxPageNum(listSize, count)}
                        getData={this.getListByPage}
                    />,
                ]
            }
        }

        const Content = () => {
            if (loading && noData) {
                return <LoadingComponent height="calc(100vh - 120px - 144px)" />
            } else {
                return (
                    <div className="screen0">
                        <div className="wrap-holder">
                            <p className="title">
                                {title}
                                <SearchTableDesc
                                    searchType={this.searchType}
                                    listSize={listSize}
                                />
                            </p>
                            <SearchInput
                                id="sub-search-input"
                                placeholder={placeholder}
                                searchKeyword={keyword}
                                changeSearch={this.getListBySearch}
                            />
                            <div className={contentsClassName}>
                                {TableContent()}
                            </div>
                        </div>
                    </div>
                )
            }
        }

        return <div className="content-wrap">{Content()}</div>
    }
}

export default SearchPage
