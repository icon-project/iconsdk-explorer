import { trackerApiInstance } from './config'
import { makeUrl } from 'utils/utils'

const chainInfo = () => {
  return localStorage.getItem("chainName");
}

export async function pushRegister(payload) {
  const trackerApi = await trackerApiInstance()
  return new Promise((resolve, reject) => {
    trackerApi.post('/v3/' + chainInfo() + '/push/register', payload)
      .then(result => {
        resolve(result)
      })
      .catch(error => {
        reject(error)
      })
  })
}

export async function pushWithdraw(payload) {
  const trackerApi = await trackerApiInstance()
  return new Promise((resolve, reject) => {
    trackerApi.delete(makeUrl('/v3/' + chainInfo() + '/push/withdraw', payload))
      .then(result => {
        resolve(result)
      })
      .catch(error => {
        reject(error)
      })
  })
}