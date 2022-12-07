import { makeUrl } from 'utils/utils'
import { trackerApiInstance } from './config'

const chainInfo = () => {
    return localStorage.getItem("chainName");
}

export async function searchData(payload) {
    const trackerApi = await trackerApiInstance()
    return new Promise((resolve, reject) => {
        trackerApi.get(makeUrl('/v0/' + chainInfo() + '/search/Search', payload))
            .then(result => {
                resolve(result.data.data)
            })
            .catch(error => {
                reject(error)
            })
    })
}