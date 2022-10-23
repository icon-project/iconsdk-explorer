import axios from 'axios';

export async function trackerApiInstance() {
  const apiUrl = await getTrackerApiUrl();
  return axios.create({
    baseURL: apiUrl
  });
}

export async function walletApiInstance() {
  const apiUrl = await getWalletApiUrl();
  return axios.create({
    baseURL: apiUrl
  });
}

export async function getTrackerApiUrl() {
  const configFile = await getConfigJsonFile();
  if (configFile && configFile.TRACKER_API_URL) {
    return configFile.TRACKER_API_URL;
  }

  if (process.env.REACT_APP_ENV) {
    switch (process.env.REACT_APP_ENV) {
      case 'local':
        return 'http://localhost:8080';
      default:
    }
  }

  return '/';
}

export async function getWalletApiUrl() {
  const configFile = await getConfigJsonFile();
  if (configFile && configFile.WALLET_API_URL) {
    return configFile.WALLET_API_URL;
  }

  if (process.env.REACT_APP_ENV) {
    switch (process.env.REACT_APP_ENV) {
      case 'local':
        return 'http://localhost:8080';
      default:
    }
  }

  return '/';
}

export async function getIsSoloVersion() {
  const configFile = await getConfigJsonFile();
  if (configFile && configFile.IS_SOLO_VERSION) {
    return !!configFile.IS_SOLO_VERSION;
  }

  if (process.env.REACT_APP_ENV) {
    switch (process.env.REACT_APP_ENV) {
      case 'local':
      default:
    }
  }
  return false;
}

async function getConfigJsonFile() {
  try {
    const response = await fetch('/config.json');
    const responseJson = await response.json();
    return responseJson;
  } catch (e) {
    console.error(e);
    return {};
  }
}
