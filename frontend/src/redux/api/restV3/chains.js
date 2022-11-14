import { makeUrl } from 'utils/utils'
import { trackerApiInstance } from './config'

export async function chainInfoList() {
    const trackerApi = await trackerApiInstance();
    return new Promise((resolve, reject) => {
        trackerApi.get(makeUrl('/v3/chain'))
            .then(result => {
                resolve(result.data)
            })
            .catch(error => {
                reject(error)
            })
    })
}
