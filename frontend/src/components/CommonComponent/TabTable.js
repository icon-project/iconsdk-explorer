import React, { Component } from 'react';
import {
    LoadingComponent,
} from 'components'

class TabTable extends Component {

    componentDidMount() {
        window.addEventListener("keydown", this.handleKeyDown)
    }

    componentWillUnmount() {
        window.removeEventListener("keydown", this.handleKeyDown)
    }

    handleKeyDown = (e) => {
    }

    getTabId(tabName) {
        return tabName.replace(/\s+/g, '-').toLowerCase();
    }

    render() {
        const { on, loading, TableContents } = this.props
        const Contents = () => {
            if (loading) {
                return <LoadingComponent height='513px' />
            }
            else {
                const { TABS } = this.props
                return (
                    <div className="screen1">
                        <div className="wrap-holder">
                            <div className="tab-holder">
                                <ul>
                                    {
                                        TABS.map((tab, index) => (
                                            <li key={index} id={this.getTabId(tab)} className={on === index ? 'on' : ''} onClick={() => { this.props.changeTab(index) }}>{tab}</li>
                                        ))
                                    }
                                </ul>
                            </div>
                            {TableContents(on)}
                        </div>
                    </div>
                )
            }
        }
        return Contents()
    }
}

export default TabTable
