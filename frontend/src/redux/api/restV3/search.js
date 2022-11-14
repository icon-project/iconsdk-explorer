import { makeUrl } from 'utils/utils'
import { trackerApiInstance } from './config'

const chainInfo = () => {
    return localStorage.getItem("chainName");
}

export async function searchData(payload) {
    console.log("searchData: ", payload);
    console.log(makeUrl('/v0/' + chainInfo() + '/search/Search'));
    const trackerApi = await trackerApiInstance()
    return new Promise((resolve, reject) => {
        console.log(payload, makeUrl('/v0/' + chainInfo() + '/search/Search', payload))
        trackerApi.get(makeUrl('/v0/' + chainInfo() + '/search/Search', payload))
            .then(result => {
                console.log("RESULT", result);
                resolve(result.data.data)
            })
            .catch(error => {
                reject(error)
            })
    })
}