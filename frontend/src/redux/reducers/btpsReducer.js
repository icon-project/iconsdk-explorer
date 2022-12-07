import actionTypes from '../actionTypes/actionTypes'
import {
  getState
} from 'utils/utils'
import {
  REDUX_STEP,
  INITIAL_STATE
} from 'utils/const'

const initialState = {
  btps: INITIAL_STATE['ARR'],
  btp: INITIAL_STATE['OBJ'],
  btpTx: INITIAL_STATE['ARR'],
  btpNetworks: INITIAL_STATE['ARR']
}

export function btpsReducer(state = initialState, action) {
  switch (action.type) {
    case actionTypes.btpList: return getState('ARR', REDUX_STEP.READY, state, action, 'btps')
    case actionTypes.btpListFulfilled: return getState('ARR', REDUX_STEP.FULFILLED, state, action, 'btps')
    case actionTypes.btpListRejected: return getState('ARR', REDUX_STEP.REJECTED, state, action, 'btps')

    case actionTypes.btpInfo: return getState('OBJ', REDUX_STEP.READY, state, action, 'btp')
    case actionTypes.btpInfoFulfilled: return getState('OBJ', REDUX_STEP.FULFILLED, state, action, 'btp')
    case actionTypes.btpInfoRejected: return getState('OBJ', REDUX_STEP.REJECTED, state, action, 'btp')

    case actionTypes.btpTxList: return getState('ARR', REDUX_STEP.READY, state, action, 'btpTx')
    case actionTypes.btpTxListFulfilled: return getState('ARR', REDUX_STEP.FULFILLED, state, action, 'btpTx')
    case actionTypes.btpTxListRejected: return getState('ARR', REDUX_STEP.REJECTED, state, action, 'btpTx')

    default: {
      return state
    }
  }
}
