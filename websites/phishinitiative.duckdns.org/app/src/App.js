import EmailForm from './components/EmailForm';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'semantic-ui-css/semantic.min.css'
import "./App.css";
import MyNavbar from './components/MyNavbar';
import Footer from './components/Footer';

function App() {

  return (
    <div  className="app">
      <EmailForm />
      <MyNavbar/>
      <Footer />
    </div>
  );
}

export default App;
