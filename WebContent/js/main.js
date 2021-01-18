$(function() {

    'use strict';

    $('#nav').onePageNav();

    // loader
    $.fakeLoader({
        spinner: "spinner1",
        bgColor: "#06111e"
    });

    // smooth scroll
    $("a").on("click", function(event) {

        if (this.hash !== "") {
            event.preventDefault();

            var hash = this.hash;

            $("html, body").animate({

                scrollTop: $(hash).offset().top - 50

            }, 850);

        }

    });

    // hide navbar on mobile after click
    $('.navbar-nav a').on('click', function() {
        $('.navbar-collapse').collapse('hide');
    });

    // navbar on scroll
    $(window).on("scroll", function() {

        var onScroll = $(this).scrollTop();

        if( onScroll > 50) {
            $(".navbar").addClass("navbar-fixed");
        }
        else {
            $(".navbar").removeClass("navbar-fixed");
        }

    });

    // swiper slider on testimonial
    $(document).ready(function () {
        var swiper = new Swiper ('.swiper-testimonial', {
          direction: 'horizontal',
          spaceBetween: 30,
          slidesPerView: '1',
          autoplay: {
            delay: 4000,
          }
      })
    });

});