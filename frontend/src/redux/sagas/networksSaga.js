import { fork, put, takeLatest, call } from 'redux-saga/effects'
import AT from '../actionTypes/actionTypes';
import {
  networkList as NETWORK_LIST_API,
  networkInfo as NETWORK_INFO_API,
  networkBTPList as NETWORK_BTP_LIST_API
} from '../api/restV3';

export default function* networksSaga() {
  yield fork(watchNetworkList);
  yield fork(watchNetworkInfo);
  yield fork(watchNetworkBTPList);
}

function* watchNetworkList() { yield takeLatest(AT.networkList, networkListFunc) }
function* watchNetworkInfo() { yield takeLatest(AT.networkInfo, networkInfoFunc) }
function* watchNetworkBTPList() { yield takeLatest(AT.networkBTPList, networkBTPListFunc)}

function* networkListFunc(action) {
  try {
    if (action.payload.count === 0) {
      yield put({ type: AT.networkListFulfilled, payload: { data: [] } });
      return
    }

    const payload = yield call(NETWORK_LIST_API, action.payload);
    if (payload.result === '200') {
      yield put({ type: AT.networkListFulfilled, payload: payload });
    }
    else {
      throw new Error();
    }
  }
  catch (e) {
    yield put({ type: AT.networkListRejected });
  }
}

function* networkInfoFunc(action) {
  try {
    const payload = yield call(NETWORK_INFO_API, action.payload);
    if (payload.result === '200') {
      yield put({ type: AT.networkInfoFulfilled, payload: payload });
    }
    else {
      throw new Error();
    }
  }
  catch (e) {
    yield put({ type: AT.networkInfoRejected, error: action.payload.networkId });
  }
}

function* networkBTPListFunc(action) {
  try {
    if (action.payload.count === 0) {
      yield put({ type: AT.networkBTPListFulfilled, payload: { data: [] } });
      return
    }

    const payload = yield call(NETWORK_BTP_LIST_API, action.payload);
    if (payload.result === '200') {
      yield put({ type: AT.networkBTPListFulfilled, payload: payload });
    }
    else {
      throw new Error();
    }
  }
  catch (e) {
    yield put({ type: AT.networkBTPListRejected });
  }
}
