import { trackerApiInstance } from './config'

//TODO
export async function getMainInfo() {
  const trackerApi = await trackerApiInstance()
  return new Promise((resolve, reject) => {
    trackerApi.get('/v3/main/mainInfo')
      .then(result => {
        resolve(result.data)
      })
      .catch(error => {
        reject(error)
      })
  })
}

//TODO
export async function getMainChart() {
  const trackerApi = await trackerApiInstance()
  return new Promise((resolve, reject) => {
    trackerApi.get('/v3/main/mainChart')
      .then(result => {
        resolve(result.data.data)
      })
      .catch(error => {``
        reject(error)
      })
  })
}
