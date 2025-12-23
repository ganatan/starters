function information(): string {
  console.log('00000000001:information');
  let result = 'information';
  return result;
}

function boxoffice(): string {
  console.log('00000000001:boxoffice');
  let result = 'boxoffice';
  return result;
}

function getShows(param: string, callback: () => string) {
  console.log('00000000001:getShows');
  console.log('00000000002:getShows:' + param);
  let result = callback();
  console.log('00000000003:getShows:' + result);
}

getShows('Aliens', information);
getShows('Exodus', boxoffice);



function informationMedia(name: string): string {
  console.log('00000000001:information');
  let result = 'information on : ' + name;
  return result;
}

function getMedias(param: string, callback: (mediaName: string) => string) {
  console.log('00000000001:getMedias');
  console.log('00000000002:getMedias:' + param);
  let result = callback(param);
  console.log('00000000003:getMedias:' + result);
}

getMedias('Exodus', information);
