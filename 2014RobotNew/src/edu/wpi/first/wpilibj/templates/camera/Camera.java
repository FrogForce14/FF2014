/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.camera;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.*;

/**
 *
 * @author skodali
 */
public class Camera extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    private final static Camera instance = new Camera();
    
    
    public static Camera getInstance(){
        return instance;
    }
    
   
     public static class Scores {
        double rectangularity;
        double aspectRatioVertical;
        double aspectRatioHorizontal;
    }
    public static class TargetReport {
		int verticalIndex;
		int horizontalIndex;
		boolean Hot;
		double totalScore;
		double leftScore;
		double rightScore;
		double tapeWidthScore;
		double verticalScore;
    };
    private static double ratioToScore(double ratio)
	{
		return (Math.max(0, Math.min(100*(1-Math.abs(1-ratio)), 100)));
	}
    private static double scoreRectangularity(ParticleAnalysisReport report){
            if(report.boundingRectWidth*report.boundingRectHeight !=0){
                    return 100*report.particleArea/(report.boundingRectWidth*report.boundingRectHeight);
            } else {
                    return 0;
            }	
    }
     private static double computeDistance (BinaryImage image, ParticleAnalysisReport report, int particleNumber) throws NIVisionException {
            double rectLong, height;
            int targetHeight;

            rectLong = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_LONG_SIDE);
            //using the smaller of the estimated rectangle long side and the bounding rectangle height results in better performance
            //on skewed rectangles
            height = Math.min(report.boundingRectHeight, rectLong);
            targetHeight = 32;

            return Y_IMAGE_RES * targetHeight / (height * 12 * 2 * Math.tan(VIEW_ANGLE*Math.PI/(180*2)));
    }
     public static double scoreAspectRatio(BinaryImage image, ParticleAnalysisReport report, int particleNumber, boolean vertical) throws NIVisionException
    {
        double rectLong, rectShort, aspectRatio, idealAspectRatio;

        rectLong = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_LONG_SIDE);
        rectShort = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
        idealAspectRatio = vertical ? (4.0/32) : (23.5/4);	//Vertical reflector 4" wide x 32" tall, horizontal 23.5" wide x 4" tall
	
        //Divide width by height to measure aspect ratio
        if(report.boundingRectWidth > report.boundingRectHeight){
            //particle is wider than it is tall, divide long by short
            aspectRatio = ratioToScore((rectLong/rectShort)/idealAspectRatio);
        } else {
            //particle is taller than it is wide, divide short by long
            aspectRatio = ratioToScore((rectShort/rectLong)/idealAspectRatio);
        }
	return aspectRatio;
    }
     private static boolean scoreCompare(Scores scores, boolean vertical){
	boolean isTarget = true;

	isTarget &= scores.rectangularity > RECTANGULARITY_LIMIT;
	if(vertical){
            isTarget &= scores.aspectRatioVertical > VERT_ASPECT_RATIO_LIMIT;
	} else {
            isTarget &= scores.aspectRatioHorizontal > HORIZ_ASPECT_RATIO_LIMIT;
	}

	return isTarget;
    }
    
    private Camera(){}
    
    final static int Y_IMAGE_RES = 480;		//X Image resolution in pixels, should be 120, 240 or 480 //this is just the size of the camera picture
    final static double VIEW_ANGLE = 49;		//Axis M1013 
    //final double VIEW_ANGLE = 41.7;		//Axis 206 camera
    //final double VIEW_ANGLE = 37.4;  //Axis M1011 camera
    final static double PI = 3.141592653; //obvious

    //Score limits used for target identification
    final static int  RECTANGULARITY_LIMIT = 40;
    final static int HORIZ_ASPECT_RATIO_LIMIT = 55;
    final static int VERT_ASPECT_RATIO_LIMIT = 35;//aspect ratio limit. let me see how that is calculated

    //Score limits used for hot target determination
    final static int TAPE_WIDTH_LIMIT = 50;
    final static int  VERTICAL_SCORE_LIMIT = 50;   //these are more limits
    final static int LR_SCORE_LIMIT = 50;

    //Minimum area of particles to be considered
    final static int AREA_MINIMUM = 150;
    //Minimum width
    final static int WIDTH_MINIMUM = 40;

    //Maximum number of particles to process
    final static int MAX_PARTICLES = 8;

    
   
    
    //static AxisCamera camera =  AxisCamera.getInstance();          // the axis camera object (connected to the switch) this is obvious
    static CriteriaCollection cc = new CriteriaCollection();
    static CriteriaCollection ccc = new CriteriaCollection();
    
    static ColorImage image; // next 2 lines read image from flash on cRIO// the criteria for doing the particle filter operation 
    
    static TargetReport target = new TargetReport(); 
    public static void getImage(){  
	int verticalTargets[] = new int[MAX_PARTICLES];    //yeah, each particle/blob has an identifying integer, like a memory address. these arrays hold them
	int horizontalTargets[] = new int[MAX_PARTICLES];    
	int verticalTargetCount, horizontalTargetCount;
        cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, AREA_MINIMUM, 65535, false); 
        ccc.addCriteria(NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_WIDTH, WIDTH_MINIMUM, 65535, false);
        boolean connected = false;
        int trys = 0;
        while(!connected){
                try {
                    //System.out.println("get Image");
                    Thread.sleep(100);
                    trys++;
                    //camera.getImage();
                    connected = true;
           // }  catch (NIVisionException ex) {
             //   ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            //} catch (AxisCameraException ex) {
                    System.out.println("waiting for camera");
                    if(trys >= 10){
                        System.out.println("out of time");
                        target.Hot = false;
                        return;
                    }
                }
        }
        try{                        
                
                 //ColorImage colorImage = camera.getImage();     // comment if using stored images
                //colorImage.write("/testImage.png");
                ColorImage colorImage;                           // next 2 lines read image from flash on cRIO
                colorImage = new RGBImage("/testImage.png");		// get the sample image from the cRIO flash
                BinaryImage binaryImage = colorImage.thresholdHSV(45, 160, 100, 255, 40, 255);   // keep only green objects
                binaryImage.write("/BImage.png");
                //thresholdImage.write("/threshold.bmp");
                
                
                
                binaryImage = binaryImage.convexHull(true);
                binaryImage.write("/CHImage.png");
                binaryImage = binaryImage.particleFilter(cc);           // filter out small particles
                binaryImage.write("/FImage.png");
                 //iterate through each particle and score to see if it is a target
                Scores scores[] = new Scores[binaryImage.getNumberParticles()];
                horizontalTargetCount = verticalTargetCount = 0;
                
                if(binaryImage.getNumberParticles() > 0)
                {
			for (int i = 0; i < MAX_PARTICLES && i < binaryImage.getNumberParticles(); i++) {
			ParticleAnalysisReport report = binaryImage.getParticleAnalysisReport(i);
                        scores[i] = new Scores();
					
			//Score each particle on rectangularity and aspect ratio
			scores[i].rectangularity = scoreRectangularity(report);
			scores[i].aspectRatioVertical = scoreAspectRatio(binaryImage, report, i, true);
			scores[i].aspectRatioHorizontal = scoreAspectRatio(binaryImage, report, i, false);			
					
			//Check if the particle is a horizontal target, if not, check if it's a vertical target
			if(scoreCompare(scores[i], false))
			{
                            System.out.println("particle: " + i + "is a Horizontal Target centerX: " + report.center_mass_x + "centerY: " + report.center_mass_y);
                            horizontalTargets[horizontalTargetCount++] = i; //Add particle to target array and increment count
			} else if (scoreCompare(scores[i], true)) {
                            System.out.println("particle: " + i + "is a Vertical Target centerX: " + report.center_mass_x + "centerY: " + report.center_mass_y);
                            verticalTargets[verticalTargetCount++] = i;  //Add particle to target array and increment count
			} else {
                            System.out.println("particle: " + i + "is not a Target centerX: " + report.center_mass_x + "centerY: " + report.center_mass_y);
			}
                            System.out.println("rect: " + scores[i].rectangularity + "ARHoriz: " + scores[i].aspectRatioHorizontal);
                            System.out.println("ARVert: " + scores[i].aspectRatioVertical);	
			}

			//Zero out scores and set verticalIndex to first target in case there are no horizontal targets
			target.totalScore = target.leftScore = target.rightScore = target.tapeWidthScore = target.verticalScore = 0;
			target.verticalIndex = verticalTargets[0];
                        for (int i = 0; i < verticalTargetCount; i++)
			{
				ParticleAnalysisReport verticalReport = binaryImage.getParticleAnalysisReport(verticalTargets[i]);
				for (int j = 0; j < horizontalTargetCount; j++)
				{
                                    ParticleAnalysisReport horizontalReport = binaryImage.getParticleAnalysisReport(horizontalTargets[j]);
                                    double horizWidth, horizHeight, vertWidth, leftScore, rightScore, tapeWidthScore, verticalScore, total;
	
                                    //Measure equivalent rectangle sides for use in score calculation
                                    horizWidth = NIVision.MeasureParticle(binaryImage.image, horizontalTargets[j], false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_LONG_SIDE);
                                    vertWidth = NIVision.MeasureParticle(binaryImage.image, verticalTargets[i], false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
                                    horizHeight = NIVision.MeasureParticle(binaryImage.image, horizontalTargets[j], false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
						
                                    //Determine if the horizontal target is in the expected location to the left of the vertical target
                                    leftScore = ratioToScore(1.2*(verticalReport.boundingRectLeft - horizontalReport.center_mass_x)/horizWidth);
                                    //Determine if the horizontal target is in the expected location to the right of the  vertical target
                                    rightScore = ratioToScore(1.2*(horizontalReport.center_mass_x - verticalReport.boundingRectLeft - verticalReport.boundingRectWidth)/horizWidth);
                                    //Determine if the width of the tape on the two targets appears to be the same
                                    tapeWidthScore = ratioToScore(vertWidth/horizHeight);
                                    //Determine if the vertical location of the horizontal target appears to be correct
                                    verticalScore = ratioToScore(1-(verticalReport.boundingRectTop - horizontalReport.center_mass_y)/(4*horizHeight));
                                    total = leftScore > rightScore ? leftScore:rightScore;
                                    total += tapeWidthScore + verticalScore;

                                    //If the target is the best detected so far store the information about it
                                    if(total > target.totalScore)
                                    {
                                            target.horizontalIndex = horizontalTargets[j];
                                            target.verticalIndex = verticalTargets[i];
                                            target.totalScore = total;
                                            target.leftScore = leftScore;
                                            target.rightScore = rightScore;
                                            target.tapeWidthScore = tapeWidthScore;
                                            target.verticalScore = verticalScore;
                                    }
                                }
                                //Determine if the best target is a Hot target
                                target.Hot = hotOrNot(target);
                            }

                            if(verticalTargetCount > 0)
                            {
                                    //Information about the target is contained in the "target" structure
                                    //To get measurement information such as sizes or locations use the
                                    //horizontal or vertical index to get the particle report as shown below
                                    ParticleAnalysisReport distanceReport = binaryImage.getParticleAnalysisReport(target.verticalIndex);
                                    double distance = computeDistance(binaryImage, distanceReport, target.verticalIndex);
                                    if(target.Hot)
                                    {
                                            System.out.println("Hot target located");
                                            System.out.println("Distance: " + distance);
                                    } else {
                                            System.out.println("No hot target present");
                                            System.out.println("Distance: " + distance);
                                    }
                            }
                }

                /**
                 * all images in Java must be freed after they are used since they are allocated out
                 * of C data structures. Not calling free() will cause the memory to accumulate over
                 * each pass of this loop.
                 */
                colorImage.free();
                binaryImage.free();
                        
                
              
          
                
                 
                ////////====BinaryImage thresholdImage = image.thresholdHSV(hueLow, hueHigh, saturationLow, saturationHigh, valueLow, valueHigh);   // keep only green objects
                //thresholdImage.write("/threshold.bmp");
                ////////====BinaryImage filteredImage = thresholdImage.particleFilter(cc);
        }catch(NIVisionException ex){
            
            System.out.println(ex.toString());
            
        //}catch(AxisCameraException ex){
            
            System.out.println(ex.toString());
        }
    }

    public static boolean hotOrNot(TargetReport target)
	{
                
		boolean isHot = true;
		
		isHot &= target.tapeWidthScore >= TAPE_WIDTH_LIMIT;
		isHot &= target.verticalScore >= VERTICAL_SCORE_LIMIT;
		isHot &= (target.leftScore > LR_SCORE_LIMIT) | (target.rightScore > LR_SCORE_LIMIT);
		
                
		return isHot;
	}
    
    
    
    public static boolean isHot(){
        getImage();
        return target.Hot;
    }
    
    //get these
    public boolean hotOrNot;
    public double distance;
    
    public static class CameraRunnable implements Runnable{
        public boolean isDone = false,
                       isHot;
        public void run(){
            
            isDone = false;
            this.isHot = Camera.isHot();
            isDone = true;
            
        }
        
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
