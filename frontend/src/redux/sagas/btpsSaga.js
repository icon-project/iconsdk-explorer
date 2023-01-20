import { fork, put, takeLatest, call } from 'redux-saga/effects'
import AT from '../actionTypes/actionTypes';
import {
  btpList as BTP_LIST_API,
  btpInfo as BTP_INFO_API,
  btpTxList as BTP_TX_LIST_API,
  btpMessage as BTP_MESSAGE_API
} from '../api/restV3';
import {POPUP_TYPE} from "../../utils/const";

export default function* btpsSaga() {
  yield fork(watchBTPList);
  yield fork(watchBTPInfo);
  yield fork(watchBTPTxList);
  yield fork(watchBTPMessage);
}

function* watchBTPList() { yield takeLatest(AT.btpList, btpListFunc) }
function* watchBTPInfo() { yield takeLatest(AT.btpInfo, btpInfoFunc) }
function* watchBTPTxList() { yield takeLatest(AT.btpTxList, btpTxListFunc) }
function* watchBTPMessage() { yield takeLatest(AT.btpMessage, btpMessageFunc) }

export function* btpListFunc(action) {
  try {
    if (action.payload.count === 0) {
      yield put({ type: AT.btpListFulfilled, payload: { data: [] } });
      return
    }

    const payload = yield call(BTP_LIST_API, action.payload);
    if (payload.result === '200') {
      yield put({ type: AT.btpListFulfilled, payload: payload });
    }
    else {
      throw new Error();
    }
  }
  catch (e) {
    yield put({ type: AT.btpListRejected });
  }
}

export function* btpInfoFunc(action) {
  try {
    const payload = yield call(BTP_INFO_API, action.payload);
    if (payload.result === '200') {
      yield put({ type: AT.btpInfoFulfilled, payload: payload });
    }
    else {      
      throw new Error();
    }
  }
  catch (e) {
    yield put({ type: AT.btpInfoRejected, error: action.payload.blockHeight });
  }
}

export function* btpTxListFunc(action) {
  try {
    if (action.payload.count === 0) {
      yield put({ type: AT.btpTxListFulfilled, payload: { data: [] } });
      return
    }

    const payload = yield call(BTP_TX_LIST_API, action.payload);
    if (payload.result === '200') {
      yield put({ type: AT.btpTxListFulfilled, payload: payload });
    }
    else {
      throw new Error();
    }
  }
  catch (e) {
    yield put({ type: AT.btpTxListRejected });
  }
}

export function* btpMessageFunc(action) {
  try {
    const payload = yield call(BTP_MESSAGE_API, action.payload);
    if (payload.result === '200') {
      payload.type = POPUP_TYPE.BTP_MESSAGE
      yield put({ type: AT.setPopup, payload: payload });
    }
    else {
      throw new Error();
    }
  }
  catch (e) {
    yield put({ type: AT.btpMessageRejected, error: action.payload.sequenceNumber });
  }
}