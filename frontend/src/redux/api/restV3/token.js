import { makeUrl } from 'utils/utils'
import { trackerApiInstance } from './config'

const chainInfo = () => {
  return localStorage.getItem("chainName");
}

export async function tokenList(payload) {
  payload['ircVersion'] = 'IRC2';
  const trackerApi = await trackerApiInstance()
  return new Promise((resolve, reject) => {
    trackerApi.get(makeUrl('/v3/' + chainInfo() + '/token/list', payload))
      .then(result => {
        resolve(result.data)
      })
      .catch(error => {
        reject(error)
      })
  })
}

export async function tokenTxList(payload) {
  payload['ircVersion'] = 'IRC2';
  const trackerApi = await trackerApiInstance()
  return new Promise((resolve, reject) => {
    trackerApi.get(makeUrl('/v3/' + chainInfo() + '/token/txList', payload))
      .then(result => {
        resolve(result.data)
      })
      .catch(error => {
        reject(error)
      })
  })
}

export async function tokenSummary(payload) {
  payload['ircVersion'] = 'IRC2';
  const trackerApi = await trackerApiInstance()
  return new Promise((resolve, reject) => {
    trackerApi.get(makeUrl('/v3/' + chainInfo() + '/token/summary', payload))
      .then(result => {
        resolve(result.data)
      })
      .catch(error => {
        reject(error)
      })
  })
}

export async function tokenTransfersList(payload) {
  return tokenTxList(payload)
}

export async function tokenHoldersList(payload) {
  payload['ircVersion'] = 'IRC2';
  const trackerApi = await trackerApiInstance()
  return new Promise((resolve, reject) => {
    trackerApi.get(makeUrl('/v3/' + chainInfo() + '/token/holders', payload))
      .then(result => {
        resolve(result.data)
      })
      .catch(error => {
        reject(error)
      })
  })
}