
const bearer = () => {
  let user = JSON.parse(localStorage.getItem('user'));
  if (user && user.token) {
    return  { 
      Authorization: 'Bearer ' + user.token,
      'Content-Type': 'application/json',
    }; 
  } else {
    return{};
  }
}

export default bearer();
