import { fork, put, takeLatest, call } from 'redux-saga/effects'
import AT from '../actionTypes/actionTypes';
import {
  addressList as ADDRESS_LIST_API,
  addressInfo as ADDRESS_INFO_API,
  addressTxList as ADDRESS_TX_LIST,
  addressInternalTxList as ADDRESS_INTERNAL_TX_LIST,
  addressTokenTxList as ADDRESS_TOKEN_TX_LIST
} from '../api/restV3';
import { getBalance } from '../api/restV3/icx';
import { convertLoopToIcxDecimal } from '../../utils/utils';

export default function* addressesSaga() {
  yield fork(watchAddressList);
  yield fork(watchAddressInfo);
  yield fork(watchAddressTxList);
  yield fork(watchAddressInternalTxList);
  yield fork(watchAddressTokenTxList);
}

function* watchAddressList() { yield takeLatest(AT.addressList, addressListFunc) }
function* watchAddressInfo() { yield takeLatest(AT.addressInfo, addressInfoFunc) }
function* watchAddressTxList() { yield takeLatest(AT.addressTxList, addressTxListFunc) }
function* watchAddressInternalTxList() { yield takeLatest(AT.addressInternalTxList, addressInternalTxListFunc) }
function* watchAddressTokenTxList() { yield takeLatest(AT.addressTokenTxList, addressTokenTxListFunc) }

export function* addressListFunc(action) {
  try {
    if (action.payload.count === 0) {
      yield put({ type: AT.addressListFulfilled, payload: { data: [] } });
      return
    }

    const payload = yield call(ADDRESS_LIST_API, action.payload);
    if (payload.result === '200') {
      yield put({ type: AT.addressListFulfilled, payload: payload });
    }
    else {
      throw new Error();
    }
  }
  catch (e) {
    yield put({ type: AT.addressListRejected });
  }
}

export function* addressInfoFunc(action) {
  try {
    const payload = yield call(ADDRESS_INFO_API, action.payload);
    if (payload.result === '200') {
      const { address } = action.payload
      const balance = yield call(getBalance, address)

      const _balance = !balance ? 0 : convertLoopToIcxDecimal(balance)

      let active = 'N/A', media = {}

      payload.data = {
        ...payload.data,
        available: _balance,
        active,
        media,
      }
      
      yield put({ type: AT.addressInfoFulfilled, payload: payload });
    }
    else {      
      throw new Error();
    }
  }
  catch (e) {
    yield put({ type: AT.addressInfoRejected, error: action.payload.address });
  }
}

export function* addressTxListFunc(action) {
  try {
    if (action.payload.count === 0) {
      yield put({ type: AT.addressTxListFulfilled, payload: { data: [] } });
      return
    }

    const payload = yield call(ADDRESS_TX_LIST, action.payload);
    if (payload.result === '200') {
      yield put({ type: AT.addressTxListFulfilled, payload: payload });
    }
    else {
      throw new Error();
    }
  }
  catch (e) {
    yield put({ type: AT.addressTxListRejected });
  }
}

export function* addressInternalTxListFunc(action) {
  try {
    if (action.payload.count === 0) {
      yield put({ type: AT.addressInternalTxListFulfilled, payload: { data: [] } });
      return
    }

    const payload = yield call(ADDRESS_INTERNAL_TX_LIST, action.payload);
    if (payload.result === '200') {
      yield put({ type: AT.addressInternalTxListFulfilled, payload: payload });
    }
    else {
      throw new Error();
    }
  }
  catch (e) {
    yield put({ type: AT.addressInternalTxListRejected });
  }
}

export function* addressTokenTxListFunc(action) {
  try {
    if (action.payload.count === 0) {
      yield put({ type: AT.addressTokenTxListFulfilled, payload: { data: [] } });
      return
    }

    const payload = yield call(ADDRESS_TOKEN_TX_LIST, action.payload);
    if (payload.result === '200') {
      yield put({ type: AT.addressTokenTxListFulfilled, payload: payload });
    }
    else {
      throw new Error();
    }
  }
  catch (e) {
    yield put({ type: AT.addressTokenTxListRejected });
  }
}

