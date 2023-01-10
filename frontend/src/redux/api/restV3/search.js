import { makeUrl, getChainInfo } from 'utils/utils'
import { trackerApiInstance } from './config'

export async function searchData(payload) {
    const trackerApi = await trackerApiInstance()
    return new Promise((resolve, reject) => {
        trackerApi.get(makeUrl('/v0/' + getChainInfo() + '/search/Search', payload))
            .then(result => {
                resolve(result.data.data)
            })
            .catch(error => {
                reject(error)
            })
    })
}