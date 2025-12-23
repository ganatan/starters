
function getItems(count: number) {
  const timerId = setInterval(() => {
    console.log('00000000001')
    count = count + 1;
    if (count > 5) {
      clearInterval(timerId);
    }
  }, 1000)

}

export { getItems };