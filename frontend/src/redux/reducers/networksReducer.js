import actionTypes from '../actionTypes/actionTypes'
import {
  getState
} from 'utils/utils'
import {
  REDUX_STEP,
  INITIAL_STATE
} from 'utils/const'

const initialState = {
  networks: INITIAL_STATE['ARR'],
  network: INITIAL_STATE['OBJ'],
  networkBTPs: INITIAL_STATE['ARR'],
}

export function networksReducer(state = initialState, action) {
  switch (action.type) {
    case actionTypes.networkList: return getState('ARR', REDUX_STEP.READY, state, action, 'networks')
    case actionTypes.networkListFulfilled: return getState('ARR', REDUX_STEP.FULFILLED, state, action, 'networks')
    case actionTypes.networkListRejected: return getState('ARR', REDUX_STEP.REJECTED, state, action, 'networks')

    case actionTypes.networkInfo: return getState('OBJ', REDUX_STEP.READY, state, action, 'network')
    case actionTypes.networkInfoFulfilled: return getState('OBJ', REDUX_STEP.FULFILLED, state, action, 'network')
    case actionTypes.networkInfoRejected: return getState('OBJ', REDUX_STEP.REJECTED, state, action, 'network')

    case actionTypes.networkBTPList: return getState('ARR', REDUX_STEP.READY, state, action, 'networkBTPs')
    case actionTypes.networkBTPListFulfilled: return getState('ARR', REDUX_STEP.FULFILLED, state, action, 'networkBTPs')
    case actionTypes.networkBTPListRejected: return getState('ARR', REDUX_STEP.REJECTED, state, action, 'networkBTPs')

    default: {
      return state
    }
  }
}
