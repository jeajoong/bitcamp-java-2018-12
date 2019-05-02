var header = document.querySelector('body > header');

// 자바스크립트의 자동형변환으로 인해 null 이나 undefined는 자동으로 false로 설정된다.

if (window.localStorage.getItem('email')) {
//  document.querySelector('#email').value = localStorage.getItem('email')
  document.querySelector('#email').value = localStorage.email
}

//헤더 가져오기
(function () {
  var xhr = new XMLHttpRequest()
  xhr.onreadystatechange = function() {
    if (xhr.readyState != 4 || xhr.status != 200)
      return;
    header.innerHTML = xhr.responseText
    
    // body 태그 쪽에 헤더 로딩 완료 이벤트를 보낸다.
    var e = new Event("loaded.header");
    document.body.dispatchEvent(e);
  };
  xhr.open('GET', '../header.html', true)
  xhr.send()
})();

document.querySelector('#login-btn').onclick = () => {
  var xhr = new XMLHttpRequest()
  xhr.onreadystatechange = function() {
    if (xhr.readyState != 4 || xhr.status != 200)
      return;
    
    var data = JSON.parse(xhr.responseText);
    
    if (data.status == 'success') {
      location.href = "../index.html"
        
    } else {
      alert('로그인 실패입니다!\n' + data.message);
    }
  };
  xhr.open('POST', '../../app/json/auth/login', true)
  xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  
  var email = document.querySelector('#email').value;
  var password = document.querySelector('#password').value;
  
  if (document.querySelector('#saveEmail:checked') != null) {
    //웹브라우저의 로컬 스토리지에 이메일을 저장한다.
//    window.localStorage.setItem("email", email);
    window.localStorage.email = email;
  } else {
    window.localStorage.removeItem("email");
  }
  
  var qs = 'email=' + email + '&password=' + password;
  xhr.send(qs);
};






