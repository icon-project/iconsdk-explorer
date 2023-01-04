import React, { Component } from 'react'
import { RecentBlocks, RecentTransactions } from 'components'
import { search } from 'redux/actions/searchActions';
import { connect } from 'react-redux'
import configData from "../../config/config.json"

class MainPage extends Component {

    state = {
        value: '',
        focused: false
    }

    input = null
    focused = false

    handleChange = e => {
        const { value } = e.target
        this.setState({ value })
    }

    handleKeyDown = e => {
        if (e.key === 'Enter') {
            this.props.search(this.state.value)
        }
        if (e.key === 'Escape') {
            this.setState({ value: '' }, () => {
                this.input.blur()
            })
        }
    }

    componentWillMount() {
        this.props.getMainInfo()
    }

    render() {
        return (
            <div className="content-wrap">
                <div className="screen2">
                    <div className="wrap-holder">
                        <div className="content">
                            <p>{configData.ICONSDK_EXPLORER_NAME}</p>
                            <div className="search-group txt fixing">
                                <input id='main-top-search-bar'
                                       ref={ref => {
                                           this.input = ref
                                           if (this.input) {
                                               this.input.onfocus = () => {
                                                   this.focused = true;
                                               };
                                               this.input.onblur = () => {
                                                   this.focused = false;
                                               };
                                           }
                                       }}
                                       type="text"
                                       className="txt-type-search"
                                       placeholder="Address, TxHash, Block, SCORE"
                                       value={this.state.value}
                                       onKeyDown={this.handleKeyDown}
                                       onChange={this.handleChange}
                                />
                                {this.state.value &&
                                    <em onMouseDown={() => {
                                        this.setState({ value: '' })
                                    }}>
                                        <i className="img"></i>
                                    </em>}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="screen0" />
                <div className="screen1">
                    <div className="bg">
                        <div className="wrap-holder">
                            <ul className="content">
                                <RecentBlocks {...this.props} />
                                <RecentTransactions {...this.props} />
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {};
}

function mapDispatchToProps(dispatch) {
    return {
        search: param => dispatch(search(param)),
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(MainPage);
