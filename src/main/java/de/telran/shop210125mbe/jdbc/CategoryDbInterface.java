package de.telran.shop210125mbe.jdbc;

import de.telran.shop210125mbe.model.Category;

public interface CategoryDbInterface {
    Category findById(long id);
}
