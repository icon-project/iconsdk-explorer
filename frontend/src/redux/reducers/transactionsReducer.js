import actionTypes from '../actionTypes/actionTypes';
import {
  getState
} from 'utils/utils'
import {
  REDUX_STEP,
  INITIAL_STATE
} from 'utils/const'

const initialState = {
  transaction: INITIAL_STATE['OBJ'],
  recentTx: INITIAL_STATE['ARR'],
  transactionEvents: INITIAL_STATE['ARR'],
  transactionInternalTx: INITIAL_STATE['ARR'],
}

export function transactionsReducer(state = initialState, action) {
  switch (action.type) {
    case actionTypes.transactionTxDetail: return getState('OBJ', REDUX_STEP.READY, state, action, 'transaction') 
    case actionTypes.transactionTxDetailFulfilled: return getState('OBJ', REDUX_STEP.FULFILLED, state, action, 'transaction') 
    case actionTypes.transactionTxDetailRejected: return getState('OBJ', REDUX_STEP.REJECTED, state, action, 'transaction') 

    case actionTypes.transactionRecentTx: return getState('ARR', REDUX_STEP.READY, state, action, 'recentTx') 
    case actionTypes.transactionRecentTxFulfilled: return getState('ARR', REDUX_STEP.FULFILLED, state, action, 'recentTx') 
    case actionTypes.transactionRecentTxRejected: return getState('ARR', REDUX_STEP.REJECTED, state, action, 'recentTx') 

    case actionTypes.transactionEventLogList: return getState('ARR', REDUX_STEP.READY, state, action, 'transactionEvents') 
    case actionTypes.transactionEventLogListFulfilled: return getState('ARR', REDUX_STEP.FULFILLED, state, action, 'transactionEvents') 
    case actionTypes.transactionEventLogListRejected: return getState('ARR', REDUX_STEP.REJECTED, state, action, 'transactionEvents') 

    case actionTypes.transactionInternalTxList: return getState('ARR', REDUX_STEP.READY, state, action, 'transactionInternalTx') 
    case actionTypes.transactionInternalTxListFulfilled: return getState('ARR', REDUX_STEP.FULFILLED, state, action, 'transactionInternalTx') 
    case actionTypes.transactionInternalTxListRejected: return getState('ARR', REDUX_STEP.REJECTED, state, action, 'transactionInternalTx') 

    default: {
      return state
    }
  }
}
