function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
      x.className += " responsive";
    } else {
      x.className = "topnav";
    }
  } 




  $(window).scroll(function () {
    if ($(window).scrollTop()) {
        $("nav").addClass("black");
    }
    else {
        $("nav").removeClass("black");
    }
})    