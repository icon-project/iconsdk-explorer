import { makeUrl, getChainInfo } from 'utils/utils'
import { trackerApiInstance } from './config'

export async function nftList(payload) {
  payload['ircVersion'] = 'IRC3';
  const trackerApi = await trackerApiInstance()
  return new Promise((resolve, reject) => {
    trackerApi.get(makeUrl('/v3/' + getChainInfo() + '/token/list', payload))
      .then(result => {
        resolve(result.data)
      })
      .catch(error => {
        reject(error)
      })
  })
}

export async function nftTxList(payload) {
  payload['ircVersion'] = 'IRC3';
  const trackerApi = await trackerApiInstance()
  return new Promise((resolve, reject) => {
    trackerApi.get(makeUrl('/v3/' + getChainInfo() + '/token/txList', payload))
      .then(result => {
        resolve(result.data)
      })
      .catch(error => {
        reject(error)
      })
  })
}

export async function nftSummary(payload) {
  payload['ircVersion'] = 'IRC3';
  const trackerApi = await trackerApiInstance()
  return new Promise((resolve, reject) => {
    trackerApi.get(makeUrl('/v3/' + getChainInfo() + '/token/summary', payload))
      .then(result => {
        resolve(result.data)
      })
      .catch(error => {
        reject(error)
      })
  })
}

export async function nftTransfersList(payload) {
  return nftTxList(payload)
}

export async function nftHoldersList(payload) {
  payload['ircVersion'] = 'IRC3';
  const trackerApi = await trackerApiInstance()
  return new Promise((resolve, reject) => {
    trackerApi.get(makeUrl('/v3/' + getChainInfo() + '/token/holders', payload))
      .then(result => {
        resolve(result.data)
      })
      .catch(error => {
        reject(error)
      })
  })
}