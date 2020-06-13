import React from 'react';
import { Switch , Route } from 'react-router-dom';

// /////////////////////////////
import Nav from './layout/nav.component';

const App =() => {
  return (
    <div>
      <Nav/>
        <Switch>
          <Route exact path='/' component={Home}></Route>
          <Route exact path='/Sefaresh' component={Sefaresh}></Route>
          <Route exact path='/Checkout' component={Checkout}></Route>
          <Route exact path='/Login' component={Login}></Route>
          <Route exact path='/Contact' component={Contact}></Route>
        </Switch>
    </div>
  );
}

const Home =()=>(
  <div>صفحه اصلی</div>
);

const Sefaresh =()=>(
  <div>صفحه سفارش</div>
);

const Contact =()=>(
  <div>صفحه تماس با ما</div>
);

const Checkout =()=>(
  <div>صفحه تسویه حساب</div>
);

const Login =()=>(
  <div>صفحه ورود</div>
);
export default App;
