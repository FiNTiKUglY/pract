import React, { useEffect } from 'react';
import './App.css';
import {
    RouterProvider,
    createBrowserRouter
} from "react-router-dom";
import Root from './routes/root';
import Signup from './routes/signup';
import Signin from './routes/signin';
import Books from './routes/books';
import BooksAdd from './routes/bookAdd';
import BooksUpdate, { loader as bookLoader } from './routes/bookUpdate';
import Genres from './routes/genres';
import GenresAdd from './routes/genreAdd';
import GenresUpdate, { loader as genreLoader } from './routes/genreUpdate';
import { useCookies } from 'react-cookie'
import axios from "axios"


const router = createBrowserRouter([
    {
      path: "/",
      element: <Root />,
      errorElement: <App />,
      children: [
        {
            path: "/books/add",
            element: <BooksAdd />
        },
        {
            path: "/books/update/:bookId",
            element: <BooksUpdate />,
            loader: bookLoader
        },
        {
            path: "/books",
            element: <Books />
        },
        {
            path: "/genres/add",
            element: <GenresAdd />
        },
        {
            path: "/genres/update/:genreId",
            element: <GenresUpdate />,
            loader: genreLoader
        },
        {
            path: "/genres",
            element: <Genres />
        },
        {
            path: "/signin",
            element: <Signin />
        },
        {
            path: "/signup",
            element: <Signup />
        }
      ]
    }
]);

function App() {

    const [cookies] = useCookies(['jwt_token', 'user']);

    useEffect(() => {
        if (cookies.jwt_token !== undefined) {
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + cookies.jwt_token;
        }
        else {
            axios.defaults.headers.common['Authorization'] = '';
        };
      }, []);

    return (
        <RouterProvider router={router} >

        </RouterProvider>
  );
}

export default App;
