import React, { Component } from 'react';
import { connect } from 'react-redux';
import AddressQrCode from './AddressQrCode';
import ContractDetail from './ContractDetail';
import Search from './Search';
import { initPopup } from '../../redux/actions/popupActions';
import { POPUP_TYPE } from 'utils/const';

class Popup extends Component {
  componentWillReceiveProps(nextProps) {
    const { type: current } = this.props;
    const { type: next } = nextProps;

    if (current === '' && next !== '') {
      this.openPopup(next);
    }
  }

  openPopup = type => {
    switch (type) {
      case POPUP_TYPE.QR:
        window.dispatchEvent(
          new CustomEvent('CUSTOM_FX', {
            detail: { type: 'POPUP_OPEN', param: 'qr' }
          })
        );
        break;
      case POPUP_TYPE.DETAIL:
        window.dispatchEvent(
          new CustomEvent('CUSTOM_FX', {
            detail: { type: 'POPUP_OPEN', param: 'detail' }
          })
        );
        break;
      case POPUP_TYPE.SEARCH:
        window.dispatchEvent(
          new CustomEvent('CUSTOM_FX', {
            detail: { type: 'POPUP_OPEN', param: 'search' }
          })
        );
        break;
      default:
    }
  };

  closePopup = () => {
    this.props.initPopup();
    window.dispatchEvent(
      new CustomEvent('CUSTOM_FX', {
        detail: { type: 'POPUP_CLOSE', param: '' }
      })
    );
  };
  closeDetail = () => {
    this.props.initPopup();
    window.dispatchEvent(
      new CustomEvent('CUSTOM_FX', {
        detail: { type: 'POPUP_CLOSE', param: 'detail' }
      })
    );
  };
  closeSearch = () => {
    this.props.initPopup();
    window.dispatchEvent(
      new CustomEvent('CUSTOM_FX', {
        detail: { type: 'POPUP_CLOSE', param: 'search' }
      })
    );
  };

  render() {
    const { type, data } = this.props;
    const isQr = type === POPUP_TYPE.QR;
    const qrData = isQr ? data : {};
    const isDetail = type === POPUP_TYPE.DETAIL;
    const detailData = isDetail ? data : {};
    const isSearch = type === POPUP_TYPE.SEARCH;
    const searchData = isSearch ? data : {};
    return (
      <React.Fragment>
        <div key='qr' className='popup-wrap qr'>
          <div className='dimmed' />
          <div className='popup'>
            <span className='close' onClick={this.closePopup}>
              <em className='img' />
            </span>
            {isQr && <AddressQrCode data={qrData} />}
          </div>
        </div>
        <div key='detail' className='popup-wrap detail'>
          <div className='dimmed' />
          <div className='popup contract'>
            <span className='close' onClick={this.closePopup}>
              <em className='img' />
            </span>
            {isDetail && (
              <ContractDetail
                data={detailData}
                closeDetail={this.closeDetail}
              />
            )}
          </div>
        </div>
        {isSearch && (
          <Search
            key='search'
            data={searchData}
            closeSearch={this.closeSearch}
          />
        )}
      </React.Fragment>
    );
  }
}

function mapStateToProps(state) {
  return {
    type: state.popup.type,
    data: state.popup.data
  };
}

function mapDispatchToProps(dispatch) {
  return {
    initPopup: () => dispatch(initPopup())
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(Popup);
