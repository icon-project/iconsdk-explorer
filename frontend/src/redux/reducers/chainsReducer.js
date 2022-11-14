import actionTypes from '../actionTypes/actionTypes'
import {
  getState
} from 'utils/utils'
import {
  REDUX_STEP,
  INITIAL_STATE
} from 'utils/const'

const initialState = {
  chainInfos: INITIAL_STATE['OBJARR']
}

export function chainsReducer(state = initialState, action) {
  switch (action.type) {
    case actionTypes.chainInfoList: return getState('OBJARR', REDUX_STEP.READY, state, action, 'chainInfos')
    case actionTypes.chainInfoListFulfilled: return getState('OBJARR', REDUX_STEP.FULFILLED, state, action, 'chainInfos')
    case actionTypes.chainInfoListRejected: return getState('OBJARR', REDUX_STEP.REJECTED, state, action, 'chainInfos')

    default: {
      return state
    }
  }
}
