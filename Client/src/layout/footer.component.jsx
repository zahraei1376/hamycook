import React from 'react';
// /////////////////////////////
import '../sass/layout/footer.styles.scss';


const Footer =() =>(
    <div className='footer'>
        <div className='footer__logo-box'>
            <img src={require('../img/logo.png')} className='footer__Logo'/>
        </div>
        <div className='footer__foot'>
            

            <ul className='footer__list'>
                <h6 className='footer__title'>حامی کوک</h6>
                <li className='footer__item'><a href='#' className='footer__link'>غذاها</a></li>
                <li className='footer__item'><a href='#' className='footer__link'>ت</a></li>
                <li className='footer__item'><a href='#' className='footer__link'>تماس با مت</a></li>
                <li className='footer__item'><a href='#' className='footer__link'>تماس با مت</a></li>
            </ul>
            <ul className='footer__list'>
                <h6 className='footer__title'>ارتباط با ما</h6>
                <li className='footer__item'><a href='#' className='footer__link'>درباره ی ما</a></li>
                <li className='footer__item'><a href='#' className='footer__link'>تماس با ما</a></li>
                <li className='footer__item'><a href='#' className='footer__link'>ثبت شکایات</a></li>
                <li className='footer__item'><a href='#' className='footer__link'>قوانین و مقررات</a></li>
            </ul>
            {/* <p className='footer__create-p'>createing by ZahraAlipour 2020</p> */}
        </div>
        <p className='footer__copyright'>createing by ZahraAlipour 2020</p>
    </div>
);

export default Footer;