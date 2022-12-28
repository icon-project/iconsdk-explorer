import actionTypes from '../actionTypes/actionTypes';
import {
  getState
} from 'utils/utils'
import {
  REDUX_STEP,
  INITIAL_STATE
} from 'utils/const'

const initialState = {
  nfts: INITIAL_STATE['ARR'],
  nftsSearch: INITIAL_STATE['ARR'],
  recentNftTx: INITIAL_STATE['ARR'],
  nft: INITIAL_STATE['OBJ'],
  nftTransfers: INITIAL_STATE['ARR'],
  nftHolders: INITIAL_STATE['ARR'],
}

export function nftsReducer(state = initialState, action) {
  switch (action.type) { 
    case actionTypes.nftList: return getState('ARR', REDUX_STEP.READY, state, action, 'nfts')
    case actionTypes.nftListFulfilled: return getState('ARR', REDUX_STEP.FULFILLED, state, action, 'nfts')
    case actionTypes.nftListRejected: return getState('ARR', REDUX_STEP.REJECTED, state, action, 'nfts')

    case actionTypes.nftListSearch: return getState('ARR', REDUX_STEP.READY, state, action, 'nftsSearch')
    case actionTypes.nftListSearchFulfilled: return getState('ARR', REDUX_STEP.FULFILLED, state, action, 'nftsSearch')
    case actionTypes.nftListSearchRejected: return getState('ARR', REDUX_STEP.REJECTED, state, action, 'nftsSearch')

    case actionTypes.nftTxList: return getState('ARR', REDUX_STEP.READY, state, action, 'recentNftTx')
    case actionTypes.nftTxListFulfilled: return getState('ARR', REDUX_STEP.FULFILLED, state, action, 'recentNftTx')
    case actionTypes.nftTxListRejected: return getState('ARR', REDUX_STEP.REJECTED, state, action, 'recentNftTx')

    case actionTypes.nftSummary: return getState('OBJ', REDUX_STEP.READY, state, action, 'nft')
    case actionTypes.nftSummaryFulfilled: return getState('OBJ', REDUX_STEP.FULFILLED, state, action, 'nft')
    case actionTypes.nftSummaryRejected: return getState('OBJ', REDUX_STEP.REJECTED, state, action, 'nft')

    case actionTypes.nftTransfersList: return getState('ARR', REDUX_STEP.READY, state, action, 'nftTransfers')
    case actionTypes.nftTransfersListFulfilled: return getState('ARR', REDUX_STEP.FULFILLED, state, action, 'nftTransfers')
    case actionTypes.nftTransfersListRejected: return getState('ARR', REDUX_STEP.REJECTED, state, action, 'nftTransfers')

    case actionTypes.nftHoldersList: return getState('ARR', REDUX_STEP.READY, state, action, 'nftHolders')
    case actionTypes.nftHoldersListFulfilled: return getState('ARR', REDUX_STEP.FULFILLED, state, action, 'nftHolders')
    case actionTypes.nftHoldersListRejected: return getState('ARR', REDUX_STEP.REJECTED, state, action, 'nftHolders')

    default: {
      return state
    }
  }
}
