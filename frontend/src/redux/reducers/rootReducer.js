import { combineReducers } from 'redux';
import { mainPageReducer } from './mainPageReducer'
import { addressesReducer } from './addressesReducer'
import { transactionsReducer } from './transactionsReducer'
import { blocksReducer } from './blocksReducer'
import { searchReducer } from './searchReducer'
import { routerReducer } from 'react-router-redux'
import { tokensReducer } from './tokensReducer'
import { contractsReducer } from './contractsReducer'
import { popupReducer } from './popupReducer'
import { storageReducer } from './storageReducer'
import { chainsReducer } from "./chainsReducer";
import { btpsReducer } from "./btpsReducer";
import { networksReducer } from "./networksReducer";

const rootReducer = combineReducers({
  mainPage: mainPageReducer,
  addresses: addressesReducer,
  transactions: transactionsReducer,
  blocks: blocksReducer,
  search: searchReducer,
  router: routerReducer,
  tokens: tokensReducer,
  contracts: contractsReducer,
  popup: popupReducer,
  storage: storageReducer,
  chains: chainsReducer,
  btps: btpsReducer,
  networks: networksReducer
});

export default rootReducer;
