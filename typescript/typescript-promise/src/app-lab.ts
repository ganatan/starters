
async function getItemsPromise(hasError: boolean) {
  let result = new Promise((resolve, reject) => {
    if (hasError) {
      setTimeout(() => {
        reject(false);
      }, 2000)
    } else {
      setTimeout(() => {
        resolve(true);
      }, 4000)
    }
  })
  return result;
}

async function getItemsWithoutAsync() {
  getItemsPromise(false)
    .then((value) => { console.log('getItemsWithoutAsync:4 secondes' + value) });

  getItemsPromise(true)
    .catch((error) => { console.log('getItemsWithoutAsync:2 secondes:' + error) })
}


async function getItemsWithoutAsyncImbrique() {
  getItemsPromise(false)
    .then((value) => {
      console.log('getItemsWithoutAsyncImbrique:4 secondes' + value)
      getItemsPromise(true)
        .catch((error) => { console.log('getItemsWithoutAsyncImbrique:2 secondes:' + error) })
    });
}


async function getItemsWithAsync() {
  try {
    let result = await getItemsPromise(false);
    console.log('getItemsWithAsync:4 secondes' + result)
    result = await getItemsPromise(true);
    console.log('getItemsWithAsync:2 secondes' + result)
  }
  catch (error) {
    console.log('getItemsWithAsync:00000000003:' + error)

  }
  finally {
    console.log('getItemsWithAsync:00000000004:finally')
  }
}


getItemsWithoutAsync();
getItemsWithoutAsyncImbrique();

getItemsWithAsync();

