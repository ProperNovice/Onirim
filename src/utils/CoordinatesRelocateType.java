package utils;

import utils.Enums.RelocateTypeEnum;

public enum CoordinatesRelocateType {

	INSTANCE;

	public Vector2 translateRelocateTypeCoordinatesSubstract(Vector2 coordinates,
			Vector2 dimensions, RelocateTypeEnum relocateTypeEnum) {

		switch (relocateTypeEnum) {

		case TOP_LEFT:
			break;

		case TOP_RIGHT:
			coordinates.x -= dimensions.x;
			break;

		case BOTTOM_LEFT:
			coordinates.y -= dimensions.y;
			break;

		case BOTTOM_RIGHT:
			coordinates.x -= dimensions.x;
			coordinates.y -= dimensions.y;
			break;

		case CENTER:
			coordinates.x -= dimensions.x / 2;
			coordinates.y -= dimensions.y / 2;
			break;

		}

		return coordinates;

	}

	public Vector2 translateRelocateTypeCoordinatesAdd(Vector2 coordinates, Vector2 dimensions,
			RelocateTypeEnum relocateTypeEnum) {

		switch (relocateTypeEnum) {

		case TOP_LEFT:
			break;

		case TOP_RIGHT:
			coordinates.x += dimensions.x;
			break;

		case BOTTOM_LEFT:
			coordinates.y += dimensions.y;
			break;

		case BOTTOM_RIGHT:
			coordinates.x += dimensions.x;
			coordinates.y += dimensions.y;
			break;

		case CENTER:
			coordinates.x += dimensions.x / 2;
			coordinates.y += dimensions.y / 2;
			break;

		}

		return coordinates;

	}

	public Vector2 translateRelocateTypeCoordinatesFindCenter(Vector2 coordinates,
			Vector2 dimensions, RelocateTypeEnum relocateTypeEnum) {

		switch (relocateTypeEnum) {

		case BOTTOM_LEFT:
			coordinates.x += dimensions.x / 2;
			coordinates.y -= dimensions.y / 2;
			break;

		case BOTTOM_RIGHT:
			coordinates.x -= dimensions.x / 2;
			coordinates.y -= dimensions.y / 2;
			break;

		case CENTER:
			break;

		case TOP_LEFT:
			coordinates.x += dimensions.x / 2;
			coordinates.y += dimensions.y / 2;
			break;

		case TOP_RIGHT:
			coordinates.x -= dimensions.x / 2;
			coordinates.y += dimensions.y / 2;
			break;

		}

		return coordinates;

	}

}
