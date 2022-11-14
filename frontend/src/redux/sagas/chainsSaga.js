import { fork, put, takeLatest, call } from 'redux-saga/effects'
import AT from '../actionTypes/actionTypes';
import {
  chainInfoList as CHAIN_INFO_LIST_API
} from '../api/restV3';

export default function* chainsSaga() {
  yield fork(watchChainInfoList);
}

function* watchChainInfoList() { yield takeLatest(AT.chainInfoList, chainInfoListFunc) }

export function* chainInfoListFunc(action) {
  try {
    const payload = yield call(CHAIN_INFO_LIST_API, action.payload);
    if (payload.result === '200') {
      yield put({ type: AT.chainInfoListFulfilled, payload: payload });
    }
    else {
      throw new Error();
    }
  }
  catch (e) {
    yield put({ type: AT.chainInfoListRejected });
  }
}
