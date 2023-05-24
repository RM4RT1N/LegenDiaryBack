package com.codecool.el_grande_project.util;

public class CalculateDistance {
    private static final float EARTH_RADIUS = 6371;

    public static boolean isPlaceInRange(double latitudePointCentral, double longitudePointCentral, double latitudeAnotherPoint, double longitudeAnotherPoint, double radiusInKilometers) {

        double distanceInRadians = radiusInKilometers / EARTH_RADIUS;


        double latCentral = Math.toRadians(latitudePointCentral);
        double latEdge = Math.toRadians(latitudeAnotherPoint);

        double lngCentral = Math.toRadians(longitudePointCentral);
        double lngEdge = Math.toRadians(longitudeAnotherPoint);

        double deltalng = lngEdge-lngCentral;
        double deltalat = latEdge - latCentral;

        double a = Math.sin(deltalat / 2) * Math.sin(deltalat / 2)
                + Math.cos(latCentral) * Math.cos(latEdge)
                * Math.sin(deltalng / 2) * Math.sin(deltalng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;
        System.out.println("odl " + distance);
        return distance <= radiusInKilometers;
//
    }



}
