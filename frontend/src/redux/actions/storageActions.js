import actionTypes from '../actionTypes/actionTypes';

export function setAddress(payload) {
  return {
    type: actionTypes.setAddress,
    payload
  }
}

export function setNotification(payload) {
  return {
    type: actionTypes.setNotification,
    payload
  }
}

export function clearWallet(){
  return{
    type:actionTypes.clearWallet
  }
}