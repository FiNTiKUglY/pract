import React, { useState, useEffect } from 'react';
import { getGenres, deleteGenre } from "../api/genreService"
import { Link } from "react-router-dom";

export default function Genres() {
    const [genres, setGenres] = useState([]);
    const [reload, setReload] = useState(false);

    useEffect(() => {
        loadGenres()
    }, [reload]);

    return (
        <section >
            <div className='table-title'>
                <h2>Жанры</h2>
                
                <table className="table table-striped table-hover">   
                    <thead>
                        <tr>
                            <th>
                                Название
                            </th>
                            <th>
                                Описание
                            </th>
                            <th>
                                <Link to="add" className="btn btn-success">Добавить</Link>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {genres.map(genre =>
                            <tr key={genre.id}>
                                <td>
                                    {genre.name}
                                </td>
                                <td>
                                    {genre.description}
                                </td>
                                <td>
                                    <Link to={`update/${genre.id}`} className="btn btn-info">Изменить</Link>
                                    |
                                    <button 
                                        onClick={() => removeGenre(genre.id)} 
                                        value={genre.id}
                                        className="btn btn-danger">
                                            Удалить
                                    </button>
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </section>
    );

    async function loadGenres() {
        const genres = await getGenres()
        setGenres(genres)
    }

    async function removeGenre(id) {
        await deleteGenre(id)
        setReload(!reload)
    }
}