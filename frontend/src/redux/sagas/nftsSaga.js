import { fork, put, takeLatest, call } from 'redux-saga/effects'
import AT from '../actionTypes/actionTypes';
import {
  nftList as NFT_LIST_API,
  nftTxList as NFT_TX_LIST_API,
  nftSummary as NFT_SUMMARY_API,
  nftTransfersList as NFT_TRANSFERS_LIST_API,
  nftHoldersList as NFT_HOLDERS_LIST_API
} from '../api/restV3';

function* watchNftList() { yield takeLatest(AT.nftList, nftListFunc) }
function* watchNftListSearch() { yield takeLatest(AT.nftListSearch, nftListSearchFunc) }
function* watchNftTxList() { yield takeLatest(AT.nftTxList, nftTxListFunc) }
function* watchNftSummary() { yield takeLatest(AT.nftSummary, nftSummaryFunc) }
function* watchNftTransfersList() { yield takeLatest(AT.nftTransfersList, nftTransfersListFunc) }
function* watchNftHoldersList() { yield takeLatest(AT.nftHoldersList, nftHoldersListFunc) }

export default function* nftsSaga() {
  yield fork(watchNftList)
  yield fork(watchNftListSearch)
  yield fork(watchNftTxList)
  yield fork(watchNftSummary)
  yield fork(watchNftTransfersList)
  yield fork(watchNftHoldersList)
}

function* nftListFunc(action) {
  try {
    if (action.payload.count === 0) {
      yield put({ type: AT.nftListFulfilled, payload: { data: [] } });
      return
    }

    const payload = yield call(NFT_LIST_API, action.payload);
    if (payload.result === '200') {
      yield put({ type: AT.nftListFulfilled, payload: payload });
    } else {
      throw new Error();
    }
  } catch (e) {
    yield put({ type: AT.nftListRejected });
  }
}

function* nftListSearchFunc(action) {
  try {
    if (action.payload.count === 0) {
      yield put({ type: AT.nftListSearchFulfilled, payload: { data: [] } });
      return
    }

    const payload = yield call(NFT_LIST_API, action.payload);
    if (payload.result === '200' || 'NO Data') {
      yield put({ type: AT.nftListSearchFulfilled, payload: payload });
    } else {
      throw new Error();
    }
  } catch (e) {
    yield put({ type: AT.nftListSearchRejected });
  }
}

function* nftTxListFunc(action) {
  try {
    if (action.payload.count === 0) {
      yield put({ type: AT.nftTxListFulfilled, payload: { data: [] } });
      return
    }

    const payload = yield call(NFT_TX_LIST_API, action.payload);
    if (payload.result === '200') {
      yield put({ type: AT.nftTxListFulfilled, payload: payload });
    } else {
      throw new Error();
    }
  } catch (e) {
    yield put({ type: AT.nftTxListRejected });
  }
}

function* nftSummaryFunc(action) {
  try {
    const payload = yield call(NFT_SUMMARY_API, action.payload);
    if (payload.result === '200') {
      yield put({ type: AT.nftSummaryFulfilled, payload: payload });
    } else {
      throw new Error();
    }
  } catch (e) {
    //TODO error
    yield put({ type: AT.nftSummaryRejected, error: action.payload.contractAddr });
  }
}

function* nftTransfersListFunc(action) {
  try {
    if (action.payload.count === 0) {
      yield put({ type: AT.nftTransfersListFulfilled, payload: { data: [] } });
      return
    }

    const payload = yield call(NFT_TRANSFERS_LIST_API, action.payload);
    if (payload.result === '200') {
      yield put({ type: AT.nftTransfersListFulfilled, payload: payload });
    } else {
      throw new Error();
    }
  } catch (e) {
    yield put({ type: AT.nftTransfersListRejected });
  }
}

function* nftHoldersListFunc(action) {
  try {
    if (action.payload.count === 0) {
      yield put({ type: AT.nftHoldersListFulfilled, payload: { data: [] } });
      return
    }

    const payload = yield call(NFT_HOLDERS_LIST_API, action.payload);
    if (payload.result === '200') {
      yield put({ type: AT.nftHoldersListFulfilled, payload: payload });
    } else {
      throw new Error();
    }
  } catch (e) {
    yield put({ type: AT.nftHoldersListRejected });
  }
}