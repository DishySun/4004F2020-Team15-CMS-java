function handleLoginButton(){
  let usrName = $('#usrName').val()
  $('#usrName').val('');
  let pwd = $('#pwd').val()
  $('#pwd').val('')

  if (usrName && pwd != ''){
    let loginInfo = {
      userName : usrName,
      password : pwd
    }

    $.post("loginInfo", loginInfo, function(data, status){
      console.log(status)
      console.log(data)
    })
  }
}
