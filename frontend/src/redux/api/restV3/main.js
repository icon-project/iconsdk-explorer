import { trackerApiInstance } from './config'

const chainInfo = () => {
  return localStorage.getItem("chainName");
}

export async function getMainInfo() {
  const trackerApi = await trackerApiInstance()
  return new Promise((resolve, reject) => {
    trackerApi.get('/v3/' + chainInfo() + '/main/mainInfo')
      .then(result => {
        resolve(result.data)
      })
      .catch(error => {
        reject(error)
      })
  })``
}

export async function getMainChart() {
  const trackerApi = await trackerApiInstance()
  return new Promise((resolve, reject) => {
    trackerApi.get('/v3/' + chainInfo() + '/main/mainChart')
      .then(result => {
        resolve(result.data.data)
      })
      .catch(error => {
        reject(error)
      })
  })
}
