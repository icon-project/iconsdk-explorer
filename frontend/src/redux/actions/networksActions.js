import actionTypes from '../actionTypes/actionTypes';

export function networkList(payload) {
  return {
    type: actionTypes.networkList,
    payload
  }
}

export function networkInfo(payload) {
  return {
    type: actionTypes.networkInfo,
    payload
  }
}

export function networkBTPList(payload) {
  return {
    type: actionTypes.networkBTPList,
    payload
  }
}