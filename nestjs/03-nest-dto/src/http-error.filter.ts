import {
  ArgumentsHost,
  BadRequestException,
  Catch,
  ExceptionFilter,
  HttpException,
  HttpStatus,
  NotFoundException,
} from '@nestjs/common';

@Catch()
export class HttpErrorFilter implements ExceptionFilter {
  catch(exception: unknown, host: ArgumentsHost) {
    const ctx = host.switchToHttp();
    const res = ctx.getResponse();

    if (exception instanceof NotFoundException) {
      res.status(HttpStatus.NOT_FOUND).json({ error: 'NOT_FOUND', message: 'CONTINENT_NOT_FOUND' });
      return;
    }

    if (exception instanceof BadRequestException) {
      const payload: any = exception.getResponse();
      const message = this.extractMessage(payload);
      res.status(HttpStatus.BAD_REQUEST).json({ error: 'BAD_REQUEST', message });
      return;
    }

    if (exception instanceof HttpException) {
      const status = exception.getStatus();
      res.status(status).json({ error: 'ERROR', message: 'ERROR' });
      return;
    }

    res.status(HttpStatus.INTERNAL_SERVER_ERROR).json({ error: 'ERROR', message: 'ERROR' });
  }

  private extractMessage(payload: any): string {
    const msg = payload?.message;

    if (typeof msg === 'string') {
      return msg;
    }

    if (Array.isArray(msg) && msg.length > 0) {
      const first = msg[0];
      if (typeof first === 'string') {
        return first;
      }
      if (typeof first?.constraints === 'object') {
        const values = Object.values(first.constraints);
        if (values.length > 0) {
          return String(values[0]);
        }
      }
      return String(first);
    }

    return 'BAD_REQUEST';
  }
}
