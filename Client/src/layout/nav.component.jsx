import React from 'react';
import {Link} from 'react-router-dom';
// import {Link, Router} from 'react-router-dom';
// ///////////////////
import '../sass/layout/nav.styles.scss';

const Nav = () =>(
        <div className='nav'>
            <Link to='/'> 
                <img src={require('../img/logo.png')} className='nav__logo'/>
            </Link>
            <input type="checkbox" class="nav__checkbox" id="navi-toggle" />

            <label for="navi-toggle" class="nav__button">
                <span class="nav__icon">&nbsp;</span>
            </label>

            <div className='nav__options'>
                {/* <Router> */}
                    <Link to='/Sefaresh' className='nav__option'>سفارشات</Link>
                    <Link to='/Checkout' className='nav__option'>تسویه حساب</Link>
                    <Link to='/Login' className='nav__option'>ورود/عضویت</Link>
                    <Link to='/Contact' className='nav__option'>تماس با ما</Link>
                {/* </Router> */}
            </div>
        </div>
);

export default Nav;