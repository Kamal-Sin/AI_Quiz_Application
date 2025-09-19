import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Login from "./Login";
import { API_ENDPOINTS } from "../../utils/api";

function Header() {
   const [activeMenu, setActiveMenu] = useState("home");
   const menuItems = ["Home", "Practice", "Creations", "Recents"];
   const pathMap = {
      Home: "/",
      Practice: "/Practice",
      Creations: "/Creations",
      Recents: "/Recents",
   };
   const [showNavbar, setShowNavbar] = useState(false);
   
   const clickLogout = () => {
      localStorage.removeItem("pid");
      sessionStorage.removeItem("pid");
      setUser("");
   };

   const [loginVisible, setLoginVisible] = useState(false);
   const [user, setUser] = useState("");
   
   const onLogin = (username) => {
      setUser(username);
      setLoginVisible(false);
   };

   const getUser = async () => {
      const url = API_ENDPOINTS.GET_USER;
      const pid = localStorage.getItem("pid");
      if (pid !== null) {
         try {
            const res = await fetch(url, {
               headers: {
                  pid: pid,
               },
            });

            if (res.status === 401) {
               setUser("");
               localStorage.removeItem("pid");
            } else if (res.status === 200) {
               const data = await res.text();
               setUser(data);
            }
         } catch (error) {
            setUser("");
         }
      } else {
         setUser("");
      }
   };
   
   const setVisibilty = (bool) => {
      setLoginVisible(bool);
   };

   useEffect(() => {
      if (loginVisible) {
         document.body.classList.add("no-scroll");
      } else {
         document.body.classList.remove("no-scroll");
      }
   }, [loginVisible]);

   useEffect(() => {
      getUser();
   }, []);

   return (
      <nav className="navbar">
         <div className="container d-flex justify-content-between align-items-center">
            <Link to="/" className="navbar-brand">
               Quizzie
            </Link>
            
            <div className="d-flex align-items-center gap-3">
               <ul className="navbar-nav d-none d-md-flex">
                  {menuItems.map((item) => (
                     <li key={item}>
                        <Link
                           to={pathMap[item]}
                           className={`nav-link ${activeMenu === item ? "active" : ""}`}
                           onClick={() => setActiveMenu(item)}
                        >
                           {item}
                        </Link>
                     </li>
                  ))}
               </ul>
               
               <div className="d-flex align-items-center gap-2">
                  {user === "" ? (
                     <button
                        className="btn btn-secondary"
                        onClick={() => setVisibilty(true)}
                     >
                        Login/Register
                     </button>
                  ) : (
                     <div className="d-flex align-items-center gap-2">
                        <span className="text-secondary">Welcome, {user}</span>
                        <button
                           className="btn btn-secondary"
                           onClick={clickLogout}
                        >
                           Logout
                        </button>
                     </div>
                  )}
               </div>
               
               <button 
                  className="btn btn-secondary d-md-none"
                  onClick={() => setShowNavbar(!showNavbar)}
               >
                  ☰
               </button>
            </div>
         </div>
         
         {showNavbar && (
            <div className="container-fluid">
               <div className="row">
                  <div className="col-12">
                     <ul className="navbar-nav d-flex flex-column">
                        {menuItems.map((item) => (
                           <li key={item}>
                              <Link
                                 to={pathMap[item]}
                                 className={`nav-link ${activeMenu === item ? "active" : ""}`}
                                 onClick={() => {
                                    setActiveMenu(item);
                                    setShowNavbar(false);
                                 }}
                              >
                                 {item}
                              </Link>
                           </li>
                        ))}
                     </ul>
                  </div>
               </div>
            </div>
         )}
         
         <Login loginVisible={loginVisible} setVisibilty={setVisibilty} onLogin={onLogin} />
      </nav>
   );
}

export default Header;
