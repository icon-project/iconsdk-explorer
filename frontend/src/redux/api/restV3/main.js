import { getChainInfo } from "../../../utils/utils";
import { trackerApiInstance } from './config'

export async function getMainInfo() {
  const trackerApi = await trackerApiInstance()
  return new Promise((resolve, reject) => {
    trackerApi.get('/v3/' + getChainInfo() + '/main/mainInfo')
      .then(result => {
        resolve(result.data)
      })
      .catch(error => {
        reject(error)
      })
  })
}

export async function getMainChart() {
  const trackerApi = await trackerApiInstance()
  return new Promise((resolve, reject) => {
    trackerApi.get('/v3/' + getChainInfo() + '/main/mainChart')
      .then(result => {
        resolve(result.data)
      })
      .catch(error => {
        reject(error)
      })
  })
}
