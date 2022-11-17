import { fork, put, takeLatest, call } from 'redux-saga/effects'
import AT from '../actionTypes/actionTypes';
import {
  blockList as BLOCK_LIST_API,
  blockInfo as BLOCK_INFO_API,
  blockTxList as BLOCK_TX_LIST_API
} from '../api/restV3';

export default function* blocksSaga() {
  yield fork(watchBlockList)
  yield fork(watchBlockInfo);
  yield fork(watchBlockTxList);
}

function* watchBlockList() { yield takeLatest(AT.blockList, blockListFunc) }
function* watchBlockInfo() { yield takeLatest(AT.blockInfo, blockInfoFunc) }
function* watchBlockTxList() { yield takeLatest(AT.blockTxList, blockTxListFunc) }

function* blockListFunc(action) {
  try {
    if (action.payload.count === 0) {
      yield put({ type: AT.blockListFulfilled, payload: { data: [] } });
      return
    }

    const payload = yield call(BLOCK_LIST_API, action.payload);
    if (payload.result === '200') {
      yield put({type: AT.blockListFulfilled, payload: payload});
    } else {
      throw new Error();
    }
  } catch (e) {
    yield put({type: AT.blockListRejected});
  }
}

function* blockInfoFunc(action) {
  try {
    const payload = yield call(BLOCK_INFO_API, action.payload);
    if (payload.result === '200') {
      yield put({type: AT.blockInfoFulfilled, payload: payload});
    } else {
      throw new Error();
    }
  } catch (e) {
    yield put({type: AT.blockInfoRejected, error: action.payload.height});
  }
}

function* blockTxListFunc(action) {
  try {
    if (action.payload.count === 0) {
      yield put({ type: AT.blockTxListFulfilled, payload: { data: [] } });
      return
    }

    const payload = yield call(BLOCK_TX_LIST_API, action.payload);
    if (payload.result === '200') {
      yield put({type: AT.blockTxListFulfilled, payload: payload});
    } else {
      throw new Error();
    }
  } catch (e) {
    yield put({type: AT.blockTxListRejected});
  }
}