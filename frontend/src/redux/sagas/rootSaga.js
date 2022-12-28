import { all, fork } from 'redux-saga/effects';
import mainPageSaga from './mainPageSaga';
import addressesSaga from './addressesSaga';
import blocksSaga from './blocksSaga';
import transactionsSaga from './transactionsSaga';
import searchSaga from './searchSaga';
import tokensSaga from './tokensSaga'
import contractsSaga from './contractsSaga'
import walletSaga from "./walletSaga"
import chainsSaga from "./chainsSaga";
import btpsSaga from "./btpsSaga";
import networksSaga from "./networksSaga";
import nftsSaga from "./nftsSaga";

export default function* rootSaga() {
  yield all([
    fork(mainPageSaga),
    fork(addressesSaga),
    fork(blocksSaga),
    fork(transactionsSaga),
    fork(searchSaga),
    fork(tokensSaga),
    fork(contractsSaga),
    fork(walletSaga),
    fork(chainsSaga),
    fork(btpsSaga),
    fork(networksSaga),
    fork(nftsSaga)
  ]);
}