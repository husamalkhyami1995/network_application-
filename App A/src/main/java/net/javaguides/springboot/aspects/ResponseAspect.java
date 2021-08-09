package net.javaguides.springboot.aspects;

import net.javaguides.springboot.exceptions.IllformedRequest;
import net.javaguides.springboot.exceptions.ResourceAlreadyExists;
import net.javaguides.springboot.exceptions.ResourceNotFound;
import net.javaguides.springboot.exceptions.Unauthorized;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Order(0)
@Component
public class ResponseAspect {
    @Around( "execution(* net.javaguides.springboot.web.*.*(..))")
//    @Around("execution(* net.javaguides.springboot.web.*.*(..))")
    public ResponseEntity log(ProceedingJoinPoint jp) {
        return apiEntityResponse(jp);
    }

    private ResponseEntity apiEntityResponse(ProceedingJoinPoint jp) {
        long start = System.currentTimeMillis();
        ResponseEntity res;
        try {
            res = ResponseEntity.ok(jp.proceed());
        } catch (IllformedRequest e) {
            res = ResponseEntity.badRequest().body(e.getMessage());
        } catch (InternalError e) {
            e.printStackTrace();
            res = new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } catch (ResourceNotFound e) {
            res = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Unauthorized e) {
            res = new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }catch (ResourceAlreadyExists e){
            res = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            res = new ResponseEntity(throwable.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity(new DetailedResponse(res, start), res.getStatusCode());
    }

    public static class DetailedResponse {
        public long time;
        public int code;
        public Object response;

        public DetailedResponse() {
        }

        public DetailedResponse(ResponseEntity res, long start) {
            this.response = res.getBody();
            this.time = System.currentTimeMillis() - start;
            this.code = res.getStatusCodeValue();
        }
    }

}

