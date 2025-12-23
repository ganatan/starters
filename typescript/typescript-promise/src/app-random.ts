async function getItemsPromise() {
  let result = new Promise((resolve, reject) => {
    const random = Math.random() < 0.5;
    if (random) {
      setTimeout(() => {
        console.log('getItemsPromise:00000000001:resolve')
        resolve(true);
      }, 1000);
    } else {
      setTimeout(() => {
        console.log('getItemsPromise:00000000001:reject')
        reject(false);
      }, 1000);
    }
  })
  return result;
}

async function getItems() {
  try {
    console.log('getItems:00000000001:')
    await getItemsPromise();
  }
  catch (error: unknown) {
    if (error instanceof Error) {
      console.log('getItems:00000000002:' + error.message);
    } else {
      console.log('getItems:00000000002:' + String(error));
    }
  }
  finally {
    console.log('getItems:00000000003:finally')
  }
}

setInterval(() => {
  console.log('00000000001:setInterval');
  getItems();
}, 4000)

