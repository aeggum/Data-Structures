///////////////////////////////////////////////////////////////////////////////                   
// Main Class File:  main.cpp
// File:             vector3.cpp
// Semester:         Fall 2011
//
// Author:           Adam Eggum - aeggum@wisc.edu
// CS Login:         eggum
// Lecturer's Name:  Perry Kivolowitz
//
// Author:     Justin Smith - jdsmith22@wisc.edu
// CS Login:         justins
// Lecturer's Name:  Perry Kivolowitz
//
// Most of the code was taken from http://resumbrae.com/ub/dms424_s03/libdms/Vector3.html
//////////////////////////// 80 columns wide //////////////////////////////////
#include <math.h>
#include "vector3.h"
#include <iostream>
using namespace std;
/**
* The vector3.cpp class is used to represent a 3D vector. It uses arrays for
* the implementation and there are the traditional vector functions throughout
* the code as well.  It has many useful functions used throughout the program.
* Most of the code was taken from http://resumbrae.com/ub/dms424_s03/libdms/Vector3.html
* Comments are for the most part, our own.
*/

//Constant vectors defined for convenience
const vector3 vector3::Zero(0.0, 0.0, 0.0);
const vector3 vector3::X_Axis(1.0, 0.0, 0.0);
const vector3 vector3::Y_Axis(0.0, 1.0, 0.0);
const vector3 vector3::Z_Axis(0.0, 0.0, 1.0);

/*
* Empty constructor for a vector object.  Creates a default vector of
* (0, 0, 0).
*/
vector3::vector3(void)
{
	set(0, 0, 0);
}

/*
* Constructor for vector3 that has 3 parameters: one for each dimension
* <param> x A GLfloat, x-dimension
* <param> y A GLfloat, y-dimension
* <param> z A GLfloat, z-dimension
*/
vector3::vector3(GLfloat x, GLfloat y, GLfloat z)
{
	set(x, y, z);
}

/**
* Constructor for vector3 that has only one parameter.  It takes another
* vector3 object passed by constant reference and sets the instance of
* vector3 equal to the parameter.
* <param> v A vector3 passed by constant reference that you want to set this vector3 to
*/
vector3::vector3(const vector3 &v)
{
	set(v.vec[0], v.vec[1], v.vec[2]);
}


/*
* Basic assignment operator for the vector3 class.
* Can be used as follows: vector3 v = new vector3();
* vector3 v2 = new vector3(0, 1, 1);   v = v2;
* <param> v A vector3 passed by constant reference, the vector you want a copy of
* <return> vector3 A vector3 object is returned
*/
vector3& vector3::operator= (const vector3& v)
{
	set(v.vec[0], v.vec[1], v.vec[2]);
	return *this;
}

/*
* set is a basic mutator. It allows changes of the vector.
* <param> x GLfloat, x-dimension
* <param> y Glfloat, y-dimension
* <param> z GLfloat, z-dimension
*/
void vector3::set(GLfloat x, GLfloat y, GLfloat z)
{
	vec[0] = x;
	vec[1] = y;
	vec[2] = z;
}

/*
* Overloaded operator[] allows you to treat the vector3 objects
* as if they were an array, doing vect[0-2] to access one variable
* at a time. If given a value less than 0, the value at [0] is returned. If
* a value greater than 2 is given, the value at [2] is returned.
* <param> i int; 0 for the first element, 1 for second element, 2 for third element
* <return> GLfloat& the reference of the value of the vector at the given element
*/
GLfloat& vector3::operator[](int i)
{
	if ((i >= 0) && (i < 3))
		return vec[i];
	else if (i < 0)
		return vec[0];
	else if (i > 2)
		return vec[2];
	else                    //this case shouldn't happen, but it prevents a warning.
		return vec[0];
}


/*
* Overloaded operator[] allows you to treat teh vector3 objects as if they
* were an array.  Values less than or greater than 0 and 2 will return
* vect[0] and vect[2], respectively.
* <param> i The element of the vector3 that you want the value of
* <return> GLfloat A floating point value, the value of the specified element
*/
GLfloat vector3::operator[](int i) const
{
	if ((i >= 0) && (i < 3))
		return vec[i];
	else if (i < 0)
		return vec[0];
	else if (i > 2)
		return vec[2];
	else                    //this case shouldn't happen. But it prevents a warning.
		return vec[0];
}

/**
* Overloaded operator+= allows you to use the += operation with two vector3
* objects.
* <param> v A vector3 object passed by constant reference
* <return> vector3& A vector3 object added to itself and the parameter
*/
vector3& vector3::operator+=(const vector3& v)
{
	vec[0] += v.vec[0];
	vec[1] += v.vec[1];
	vec[2] += v.vec[2];
	return *this;
}

/**
* Overloaded operator-= allows you to use the *= operation with two vector3
* objects.
* <param> v A vector3 object passed by constant reference
* <return> vector3& A vector3 object with itself subtracted by the parameter
*/
vector3& vector3::operator-=(const vector3& v)
{
	vec[0] -= v.vec[0];
	vec[1] -= v.vec[1];
	vec[2] -= v.vec[2];
	return *this;
}


/**
* Overloaded operator*= allows you to use the *= operation with a vector3 object
* and a scalar
* <param> s A GLfloat value that you want to multiply the vector3 by
* <return> vector3& A vector3 object with itself multiplied by the parameter
*/
vector3& vector3::operator*=(GLfloat s)
{
	vec[0] *= s;
	vec[1] *= s;
	vec[2] *= s;
	return *this;
}

/**
* Overloaded operator/= allows you to use the /= operation with a vector3 object
* and a scalar
* <param> s A GLfloat value that you want to divide the vector3 object by
* <return> vector3& A vector3 object with itself divided by the parameter
*/
vector3& vector3::operator/=(GLfloat s)
{
	vec[0] /= s;
	vec[1] /= s;
	vec[2] /= s;
	return *this;
}

/**
* Overloaded operator+ allows you to use the + operator with two vector3 objects.
* <param> v A vector3 passed by constant reference
* <return> A new vector that is the sum of the two vectors being added together
*/
vector3 vector3::operator+(const vector3& v) const
{
	return vector3(vec[0]+v.vec[0], vec[1]+v.vec[1], vec[2]+v.vec[2]);
}


/**
* Overloaded operator- allows you to use the - operator with two vector3 objects.
* <param> v A vector3 passed by constant reference
* <return> A new vector that is the difference of the two vectors
*/
vector3 vector3::operator-(const vector3& v) const
{
	return vector3(vec[0]-v.vec[0], vec[1]-v.vec[1], vec[2]-v.vec[2]);
}


/**
* This is a unary minus operator that returns the negative of the vector3
* <return> A vector3 that is the negative of the given vector3
*/
vector3 vector3::operator-(void) const
{
	return vector3(-vec[0],-vec[1], -vec[2]);
}


/**
* Overloaded operator* allows you to multiply a vector3 by a scalar. The
* function returns the product of the two, a vector3
* <param> s A GLfloat that you want to multiply the vector by
* <return> A new vector3 object that is the product of the scalar and original vector3
*/
vector3 vector3::operator*(GLfloat s) const
{
	return vector3(vec[0]*s, vec[1]*s, vec[2]*s);
}

/**
* Overloaded operator/ allows you to divide a vector3 by a scalar. The
* function returns a vector3 with each element divided by the scalar
* <param> s A GLfloat scalar that you want to divide the vector by
* <return> A new vector3 object where each element has been divided by the scalar
*/
vector3 vector3::operator/(GLfloat s) const
{
	return vector3(vec[0]/s, vec[1]/s, vec[2]/s);
}

/**
* Overloaded operator== compares two vectors element by element, returning
* true only if each of the three elements are the same
* <param> v A vector3 object passed by constant reference
* <return> true or false, dependent on whether the two vector3 objects are the same
*/
bool vector3::operator==(const vector3& v) const
{
	return ((vec[0] == v.vec[0]) &&
		(vec[1] == v.vec[1]) &&
		(vec[2] == v.vec[2]));
}

/**
* Overloaded operator!= compares two vectors element by element, returning
* true if at least one of the three elements are different
* <param> v A vector3 object passed by constant reference
* <return> true if the two vector3 objects are different, false otherwise
*/
bool vector3::operator!=(const vector3& v) const
{
	return ((vec[0] != v.vec[0]) ||
		(vec[1] != v.vec[1]) ||
		(vec[2] != v.vec[2]));
}

/**
* A simple method that returns the length of the vector
* <return> A GLfloat, the length of the vector3 object
*/
GLfloat vector3::length(void) const
{
	return sqrt(lengthSquared());
}

/**
* A simple function taht returns the length of the vector3 object squared.
* <return> A GLfloat, the length of the vector3 object sqaured
*/
GLfloat vector3::lengthSquared(void) const
{
	return vec[0]*vec[0] + vec[1]*vec[1] + vec[2]*vec[2];
}

/**
* A function that normalizes the vector. It calculates the length of the
* vector and uses that to do the normalization. There are no parameters and
* nothing is returned.  (Normalize means to scale the vector so that the length
* is 1)
*/
void vector3::normalize(void)
{
	float len = length();
	if (len > 0)
	{
		vec[0] /= len;
		vec[1] /= len;
		vec[2] /= len;
	}
}

/**
* A simple function that computes the dot product of two vectors.
* It follows the math of a dot product and returns a GLfloat
* <param> v A vector3 passed by constant reference
* <return> A GLfloat that is the dot product of the two vectors
*/
GLfloat vector3::dot(const vector3& v) const
{
	return vec[0]*v.vec[0] + vec[1]*v.vec[1] + vec[2]*v.vec[2];
}


/**
* Computes the cross product of the two vectors
* <param> v A vector3 passed by constant reference
* <return> A new vector3 object created from the cross of the two vectors
*/
vector3 vector3::cross(const vector3& v) const
{
	return vector3(vec[1] * v.vec[2] - vec[2] * v.vec[1],
		vec[2] * v.vec[0] - vec[0] * v.vec[2],
		vec[0] * v.vec[1] - vec[1] * v.vec[0]);
}


/**
* A simple method that calculates the distance between two vectors
* <param> v A vector3 object passed by constant reference
* <return> A GLfloat that is the distance between the two vectors
*/
GLfloat vector3::distance(const vector3& v) const
{
	return sqrt(distanceSquared(v));
}


/**
* Calculates the distance between two vectors, squared. It is more or less
* being used as a helper method, but for now it is public.
* <param> v A vector3 object passed by constant reference
* <return> A GLfloat that is the distance between the two vectors squared
*/
GLfloat vector3::distanceSquared(const vector3& v) const
{
	float dx, dy, dz;
	dx = vec[0] - v.vec[0];
	dy = vec[1] - v.vec[1];
	dz = vec[2] - v.vec[2];
	return dx*dx + dy*dy + dz*dz;
}


/**
* Draws a line of the vector3, from the origin to where the vector3 is located.
*/
void vector3::drawLine(void) const
{
	glBegin(GL_LINES);
	glVertex3f(0, 0, 0);
	glVertex3fv(vec);
	glEnd();
}
