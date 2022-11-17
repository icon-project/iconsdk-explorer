$(document).ready(function() {
    $(document).mouseup(function(e) {
        var container = $('.combo-group')
        if (container.has(e.target).length === 0) {
            container.css('display', 'none')
            $('.table-typeB td .balance').removeClass('on')
        }
        var status = $('.header-wrap .logo')
        if (status.has(e.target).length === 0) {
            $('.status-holder ul').css('display', 'none')
            $('.status-holder > span').removeClass('on')
        }
    })

    $('.footer-wrap .chain')
        .mouseenter(function() {
            $('.footer-wrap .chain ul')
                .stop()
                .slideDown(300, 'swing')
        })
        .mouseleave(function() {
            $('.footer-wrap .chain ul')
                .stop()
                .slideUp(300, 'swing')
        })

    addMouseInOutListener('.header-wrap ul li, .header-wrap .connect.join')
})

$(window).on('CUSTOM_FX', function(e) {
    var type = e.originalEvent.detail.type
    var param = e.originalEvent.detail.param
    switch (type) {
        case 'SET_WALLET':
            addMouseInOutListener('.header-wrap .connect.join')
            break
        case 'POPUP_OPEN':
            $('.popup-wrap.' + param).fadeIn(350)
            if (param == 'about') {
                $('.popup-wrap.about').css({ display: 'flex' })
            }
            break
        case 'POPUP_CLOSE':
            if (param == 'detail') {
                $('.popup-wrap.detail').css({ display: 'none' })
            }
            else {
                $('.popup-wrap').fadeOut(350)
            }
            break
        case 'CONTRACT_OVER':
            $('.table-typeB .help.' + param).addClass('animate')
            break
        case 'CONTRACT_OUT':
            $('.table-typeB .help.' + param).removeClass('animate')
            break
        case 'SORT_ENTER_TOP':
            $('.sort-holder-top ul')
                .stop()
                .slideDown(300, 'swing')
            break
        case 'SORT_LEAVE_TOP':
            $('.sort-holder-top ul')
                .stop()
                .slideUp(300, 'swing')
            break
        case 'SORT_ENTER':
            $('.sort-holder ul')
                .stop()
                .slideDown(300, 'swing')
            break
        case 'SORT_LEAVE':
            $('.sort-holder ul')
                .stop()
                .slideUp(300, 'swing')
            break
        case 'TOKEN_BALANCE':
            $('.combo-group').css({ display: 'block' })
            $('.table-typeB td .balance').addClass('on')
            break
        case 'CONTRACT_STATUS':
            $('.status-holder ul')
                .stop()
                .slideDown(300, 'swing')
            $('.status-holder > span').addClass('on')
            break

        default:
    }
})

function addMouseInOutListener(elements) {
    $(elements)
        .mouseenter(function() {
            $(this)
                .find('.sub-menu')
                .stop()
                .slideDown(300, 'swing')
            $(this)
                .find('span')
                .addClass('on')
        })
        .mouseleave(function() {
            $(this)
                .find('.sub-menu')
                .stop()
                .slideUp(300, 'swing')
            $(this)
                .find('span')
                .removeClass('on')
        })
}
