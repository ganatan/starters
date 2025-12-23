
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

async function getItems() {
  try {
    let result = await getItemsPromise(false);
    console.log('getItemsWithAsync:4 secondes' + result)
    result = await getItemsPromise(true);
  }
  catch (error) {
    console.log('getItemsWithAsync:00000000003:' + error)

  }
  finally {
    console.log('getItemsWithAsync:00000000004:finally')
  }
}

getItems();