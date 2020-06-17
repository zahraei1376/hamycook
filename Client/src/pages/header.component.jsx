import React from 'react';
// ///////////////////////////
import '../sass/pages/header.styles.scss';

const Header = () =>(
    <div className='header' >
        <img className='header__img' src={require('../img/header2.jpg')}  alt='header img' />
        <div className='header__text'>
            <h2 className='header__text-main'>سفارش آنلاین غذاهای سنتی و خانگی </h2>
            <h5 className='header__text-sub'>سلامتی با حامی کوک</h5>
        </div>
    </div>
);

export default Header;